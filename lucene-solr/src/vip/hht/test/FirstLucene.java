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
//	��һ��������һ��java���̣�������jar����
	
//	�ڶ���������һ��indexwriter����
	
	//	1��ָ��������Ĵ��λ��Directory����
	//	2��ָ��һ�������������ĵ����ݽ��з�����
	
	
//	�ڶ���������document����
//	������������field���󣬽�field��ӵ�document�����С�
//	���Ĳ���ʹ��indexwriter����document����д�������⣬�˹��̽�����������������������document����д�������⡣
//	���岽���ر�IndexWriter����

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
//		searchAll();
		testTokenStream();
	}

	private static void createIndex() throws IOException {
		File indexFile = new File("E:\\testJava\\testLucene");
		//Ŀ���ļ�
		Directory directory = FSDirectory.open(indexFile);
		//����������
		StandardAnalyzer analyzer = new StandardAnalyzer();
		//д������ʱ������
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		//���������ļ�
		IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
//		new Field ������4���� 
//		1,�ļ�����   StringField(FieldName, FieldValue,Store.YES)) ,TextField(FieldName, FieldValue, Store.NO) 
//		2,�ļ���С LongField(FieldName, FieldValue,Store.YES), StringField(FieldName, FieldValue,Store.YES))
//		3,�ļ�·�� StoredField(FieldName, FieldValue) 
//		4,�ļ����� TextField(FieldName, FieldValue, Store.NO)
		File searchSource = new File("E:\\testJava\\searchsource\\searchsource");
		File[] files = searchSource.listFiles();
		for (File file : files) {
			//1,�ļ����� 
			String fileName = file.getName();
			TextField fileNameField = new TextField("file_name",fileName,Store.YES);
			//2,�ļ���С
			long fileSize = FileUtils.sizeOf(file);
			LongField fileSizeField = new LongField("file_size", fileSize, Store.YES);
			//3,�ļ�·��
			String filePath = file.getAbsolutePath();
			StoredField filePathField = new StoredField("file_path", filePath);
			//4,�ļ�����
			String fileContent = FileUtils.readFileToString(file);
			TextField fileContentField = new TextField("file_content",fileContent,Store.YES);
			//����Doucument����,������Ž�ȥ
			Document document = new Document();
			document.add(fileNameField);
			document.add(fileSizeField);
			document.add(filePathField);
			document.add(fileContentField);
			//ʹ������д�����д������
			indexWriter.addDocument(document);
		}
		indexWriter.close();
	}
	
	public static void testSearch() throws IOException{
//		��һ��������һ��Directory����Ҳ�����������ŵ�λ�á�
		Directory directory = FSDirectory.open(new File("E:\\testJava\\testLucene\\"));
		
//		�ڶ���������һ��indexReader������Ҫָ��Directory����
		IndexReader indexReader = DirectoryReader.open(directory);
		
//		������������һ��indexsearcher������Ҫָ��IndexReader����
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		
//		���Ĳ�������һ��TermQuery����ָ����ѯ����Ͳ�ѯ�Ĺؼ��ʡ�
		Term term = new Term("file_name","springmvc.txt");
		TermQuery termQuery = new TermQuery(term);
//		���岽��ִ�в�ѯ��
		TopDocs topDocs = indexSearcher.search(termQuery, 10);
//		�����������ز�ѯ�����������ѯ����������
		int totalHits = topDocs.totalHits;
		System.out.println("����ѯ��"+totalHits+"���ļ�");
		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			Document doc2 = indexSearcher.doc(scoreDoc.doc);
			IndexableField fileName = doc2.getField("file_name");
			System.out.println(fileName);
		}
//		���߲����ر�IndexReader����
		indexReader.close();
		
		
	}
	
	//�鿴��׼�������ķִ�Ч��
		public static void testTokenStream() throws Exception {
			//����һ����׼����������
//			Analyzer analyzer = new StandardAnalyzer();
			IKAnalyzer analyzer = new IKAnalyzer();
			//���tokenStream����
			//��һ����������������������һ��
			//�ڶ���������Ҫ�������ı�����
//			TokenStream tokenStream = analyzer.tokenStream("test", "The Spring Framework provides "
//					+ "a comprehensive programming and configuration model.");
			TokenStream tokenStream =
					analyzer.tokenStream("test", "�����й���");
			//���һ�����ã����Ի��ÿ���ؼ���
			CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
			//���һ��ƫ���������ã���¼�˹ؼ��ʵĿ�ʼλ���Լ�����λ��
			OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
			//��ָ��������б��ͷ��
			tokenStream.reset();
			//�����ؼ����б�ͨ��incrementToken�����ж��б��Ƿ����
			while(tokenStream.incrementToken()) {
				//�ؼ��ʵ���ʼλ��
				System.out.println("start->" + offsetAttribute.startOffset());
				//ȡ�ؼ���
				System.out.println(charTermAttribute);
				//����λ��
				System.out.println("end->" + offsetAttribute.endOffset());
			}
			tokenStream.close();
		}

		
	


}
