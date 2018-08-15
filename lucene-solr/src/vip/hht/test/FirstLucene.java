package vip.hht.test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class FirstLucene {
//	第一步：创建一个java工程，并导入jar包。
	
//	第二步：创建一个indexwriter对象。
	
	//	1）指定索引库的存放位置Directory对象
	//	2）指定一个分析器，对文档内容进行分析。
	
	
//	第二步：创建document对象。
//	第三步：创建field对象，将field添加到document对象中。
//	第四步：使用indexwriter对象将document对象写入索引库，此过程进行索引创建。并将索引和document对象写入索引库。
//	第五步：关闭IndexWriter对象。

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
//		searchAll();
		testTokenStream();
	}

	private static void createIndex() throws IOException {
		File indexFile = new File("E:\\testJava\\testLucene");
		//目标文件
		Directory directory = FSDirectory.open(indexFile);
		//创建分析器
		StandardAnalyzer analyzer = new StandardAnalyzer();
		//写入索引时的配置
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		//创建索引文件
		IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
//		new Field 分析有4个域 
//		1,文件名称   StringField(FieldName, FieldValue,Store.YES)) ,TextField(FieldName, FieldValue, Store.NO) 
//		2,文件大小 LongField(FieldName, FieldValue,Store.YES), StringField(FieldName, FieldValue,Store.YES))
//		3,文件路径 StoredField(FieldName, FieldValue) 
//		4,文件内容 TextField(FieldName, FieldValue, Store.NO)
		File searchSource = new File("E:\\testJava\\searchsource\\searchsource");
		File[] files = searchSource.listFiles();
		for (File file : files) {
			//1,文件名称 
			String fileName = file.getName();
			TextField fileNameField = new TextField("file_name",fileName,Store.YES);
			//2,文件大小
			long fileSize = FileUtils.sizeOf(file);
			LongField fileSizeField = new LongField("file_size", fileSize, Store.YES);
			//3,文件路径
			String filePath = file.getAbsolutePath();
			StoredField filePathField = new StoredField("file_path", filePath);
			//4,文件内容
			String fileContent = FileUtils.readFileToString(file);
			TextField fileContentField = new TextField("file_content",fileContent,Store.YES);
			//创建Doucument对象,并把域放进去
			Document document = new Document();
			document.add(fileNameField);
			document.add(fileSizeField);
			document.add(filePathField);
			document.add(fileContentField);
			//使用索引写入对象写入索引
			indexWriter.addDocument(document);
		}
		indexWriter.close();
	}
	
	public static void testSearch() throws IOException{
//		第一步：创建一个Directory对象，也就是索引库存放的位置。
		Directory directory = FSDirectory.open(new File("E:\\testJava\\testLucene\\"));
		
//		第二步：创建一个indexReader对象，需要指定Directory对象。
		IndexReader indexReader = DirectoryReader.open(directory);
		
//		第三步：创建一个indexsearcher对象，需要指定IndexReader对象
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		
//		第四步：创建一个TermQuery对象，指定查询的域和查询的关键词。
		Term term = new Term("file_name","springmvc.txt");
		TermQuery termQuery = new TermQuery(term);
//		第五步：执行查询。
		TopDocs topDocs = indexSearcher.search(termQuery, 10);
//		第六步：返回查询结果。遍历查询结果并输出。
		int totalHits = topDocs.totalHits;
		System.out.println("共查询到"+totalHits+"个文件");
		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			Document doc2 = indexSearcher.doc(scoreDoc.doc);
			IndexableField fileName = doc2.getField("file_name");
			System.out.println(fileName);
		}
//		第七步：关闭IndexReader对象
		indexReader.close();
		
		
	}
	
	//查看标准分析器的分词效果
		public static void testTokenStream() throws Exception {
			//创建一个标准分析器对象
//			Analyzer analyzer = new StandardAnalyzer();
			IKAnalyzer analyzer = new IKAnalyzer();
			//获得tokenStream对象
			//第一个参数：域名，可以随便给一个
			//第二个参数：要分析的文本内容
//			TokenStream tokenStream = analyzer.tokenStream("test", "The Spring Framework provides "
//					+ "a comprehensive programming and configuration model.");
			TokenStream tokenStream =
					analyzer.tokenStream("test", "我是中国人");
			//添加一个引用，可以获得每个关键词
			CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
			//添加一个偏移量的引用，记录了关键词的开始位置以及结束位置
			OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
			//将指针调整到列表的头部
			tokenStream.reset();
			//遍历关键词列表，通过incrementToken方法判断列表是否结束
			while(tokenStream.incrementToken()) {
				//关键词的起始位置
				System.out.println("start->" + offsetAttribute.startOffset());
				//取关键词
				System.out.println(charTermAttribute);
				//结束位置
				System.out.println("end->" + offsetAttribute.endOffset());
			}
			tokenStream.close();
		}

		
	


}
