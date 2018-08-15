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
 * Lucene�����Ĺ���(��ɾ�ò�),��ѯ�����ַ�ʽ,һ��ֱ�Ӵ���Query�������ѯ,��һ�ַ�����ͨ��QueryParse�����Query���﷨ת����Query�����ѯ
 * @author zhoumo
 *
 */
public class LuceneManager {
	
	
	
	/**
	 * �޸�����(ԭ������ɾ��ָ��������,������һ���µ�����)
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
	 * ʹ�� Qurey�������ѯ,��ѯ����Ŀ¼�е������ĵ�
	 * @throws Exception 
	 */
	@Test
	public  void searchAll() throws Exception{
		IndexSearcher indexSearcher = getIndexSearcher();
		Query query = new MatchAllDocsQuery();
		printResult(query,indexSearcher);
		indexSearcher.getIndexReader().close();
		
		
	}
	
	//10.1.3.	NumericRangeQuery ������ֵ��Χ��ѯ
	@Test
	public void searcherRange() throws IOException{
		IndexSearcher indexSearcher = getIndexSearcher();
		NumericRangeQuery query = NumericRangeQuery.newLongRange("file_size", 47l, 200l, true, true);
		printResult(query,indexSearcher);
		indexSearcher.getIndexReader().close();
		
	}
	
	//10.1.4.	BooleanQuery   ���������ѯ
	@Test
	public void searcherBooleanQuery() throws IOException{
		IndexSearcher is = getIndexSearcher();
		BooleanQuery bq = new BooleanQuery();//�½�һ����ϲ�ѯ����
		TermQuery q1 = new TermQuery(new Term("file_name","apache"));//�½�������ȷ��ѯ
		TermQuery q2 = new TermQuery(new Term("file_content","apache"));
		bq.add(q1, Occur.MUST);//�Ѿ���Ĳ�ѯ�����һ��
		bq.add(q2, Occur.SHOULD);
		printResult(bq, is);
		is.getIndexReader().close();
		
	}
	
	@Test
	public void searcherByQueryParse() throws ParseException, IOException{
		IndexSearcher indexSearcher = getIndexSearcher();
		//�½�һ����ѯת����,ָ��һ��Ĭ�ϲ�ѯ���һ���������
		QueryParser pq  = new QueryParser("file_name", new IKAnalyzer());
//		Query q = pq.parse("file_content:apache");
		Query q = pq.parse("file_name:spring.txt");
		printResult(q, indexSearcher);
		indexSearcher.getIndexReader().close();
		
	}

	/**
	 * ���Դ�ӡ���
	 */
	private static void printResult(Query query, IndexSearcher indexSearcher) throws IOException {
		TopDocs search = indexSearcher.search(query, 20);
		
		ScoreDoc[] scoreDocs = search.scoreDocs;//�����ĵ�
		for (ScoreDoc scoreDoc : scoreDocs) {
			int doc = scoreDoc.doc;//ÿ�������ĵ���id
			Document document = indexSearcher.doc(doc);//�����ĵ���id�ҵ��ĵ�
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
	 * ��ȡ������ѯ����
	 */
	private static IndexSearcher getIndexSearcher() throws IOException {
//		��һ��������һ��Directory����Ҳ�����������ŵ�λ�á�
		Directory directory = FSDirectory.open(new File("E:\\testJava\\testLucene\\"));
//		�ڶ���������һ��indexReader������Ҫָ��Directory����
		IndexReader indexReader = DirectoryReader.open(directory);
//		������������һ��indexsearcher������Ҫָ��IndexReader����
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		return indexSearcher;
	}
	
	
}
