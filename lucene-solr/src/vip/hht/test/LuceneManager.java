package vip.hht.test;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * Lucene索引的管理(增删该查),查询有两种方式,一种直接创建Query的子类查询,另一种方法是通过QueryParse对象把Query的语法转化成Query对象查询
 * @author zhoumo
 *
 */
public class LuceneManager {
	
	
	
	/**
	 * 修改索引(原理是先删除指定的索引,再新增一个新的索引)
	 * @throws IOException 
	 */
	@Test
	public void updateIndex() throws IOException{
		IndexWriter indexWriter = getIndexWriter();
		Document document = new Document();
		TextField textField = new TextField("file_name","zhoumo.txt",Store.YES);
		document.add(textField);
		indexWriter.updateDocument(new Term("file_name","spring.txt"), document, new IKAnalyzer());
		indexWriter.close();
		
	}

	public IndexWriter getIndexWriter() throws IOException{
		 FSDirectory directory = FSDirectory.open(new File("E:\\testJava\\testLucene\\"));
		 IKAnalyzer analyzer = new IKAnalyzer();
		 IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		 IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);	
		 return indexWriter;
		
		
	}
	/**
	 * 使用 Qurey的子类查询,查询索引目录中的所有文档
	 * @throws Exception 
	 */
	@Test
	public  void searchAll() throws Exception{
		IndexSearcher indexSearcher = getIndexSearcher();
		Query query = new MatchAllDocsQuery();
		printResult(query,indexSearcher);
		indexSearcher.getIndexReader().close();
		
		
	}
	
	//10.1.3.	NumericRangeQuery 根据数值范围查询
	@Test
	public void searcherRange() throws IOException{
		IndexSearcher indexSearcher = getIndexSearcher();
		NumericRangeQuery query = NumericRangeQuery.newLongRange("file_size", 47l, 200l, true, true);
		printResult(query,indexSearcher);
		indexSearcher.getIndexReader().close();
		
	}
	
	//10.1.4.	BooleanQuery   组合条件查询
	@Test
	public void searcherBooleanQuery() throws IOException{
		IndexSearcher is = getIndexSearcher();
		BooleanQuery bq = new BooleanQuery();//新建一个组合查询条件
		TermQuery q1 = new TermQuery(new Term("file_name","apache"));//新建两个精确查询
		TermQuery q2 = new TermQuery(new Term("file_content","apache"));
		bq.add(q1, Occur.MUST);//把具体的查询组合在一起
		bq.add(q2, Occur.SHOULD);
		printResult(bq, is);
		is.getIndexReader().close();
		
	}
	
	@Test
	public void searcherByQueryParse() throws ParseException, IOException{
		IndexSearcher indexSearcher = getIndexSearcher();
		//新建一个查询转换器,指定一个默认查询域和一个域分析器
		QueryParser pq  = new QueryParser("file_name", new IKAnalyzer());
//		Query q = pq.parse("file_content:apache");
		Query q = pq.parse("file_name:spring.txt");
		printResult(q, indexSearcher);
		indexSearcher.getIndexReader().close();
		
	}

	/**
	 * 测试打印结果
	 */
	private static void printResult(Query query, IndexSearcher indexSearcher) throws IOException {
		TopDocs search = indexSearcher.search(query, 20);
		
		ScoreDoc[] scoreDocs = search.scoreDocs;//评分文档
		for (ScoreDoc scoreDoc : scoreDocs) {
			int doc = scoreDoc.doc;//每个评分文档的id
			Document document = indexSearcher.doc(doc);//根据文档的id找到文档
			String name = document.get("file_name");
//			String size = document.get("file_size");
//			String path = document.get("file_path");
			String content = document.get("file_content");
			System.out.println("FileName**********************"+name+"*********************FileName");
			System.out.println(content);
			System.out.println("========================");
		}
	}

	/**
	 * 获取索引查询对象
	 */
	private static IndexSearcher getIndexSearcher() throws IOException {
//		第一步：创建一个Directory对象，也就是索引库存放的位置。
		Directory directory = FSDirectory.open(new File("E:\\testJava\\testLucene\\"));
//		第二步：创建一个indexReader对象，需要指定Directory对象。
		IndexReader indexReader = DirectoryReader.open(directory);
//		第三步：创建一个indexsearcher对象，需要指定IndexReader对象
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		return indexSearcher;
	}
	
	
}
