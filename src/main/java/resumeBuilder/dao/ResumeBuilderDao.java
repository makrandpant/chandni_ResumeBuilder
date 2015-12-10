package resumeBuilder.dao;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.StyleSheet;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.tika.exception.TikaException;
import org.apache.tika.language.LanguageIdentifier;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ToXMLContentHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import resumeBuilder.domain.FileUploadForm;

@Repository
public class ResumeBuilderDao implements IResumeBuilderDao {

	@Override
	public MultipartFile tikaExtracter(FileUploadForm uploadForm) {
		//Import a file and save that file in Doc format
		try {
            byte[] bytes = uploadForm.getFile().getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File("D:\\resume\\"+uploadForm.getName()+".doc")));
            stream.write(bytes);
            stream.close();
            System.out.println("You successfully uploaded "
            		+uploadForm.getName());
           
        } catch (Exception e) {
        	System.out.println("You failed to upload" +uploadForm.getName());
        }
		//Parsing Using Tika
		try{
		File file = new File("D:\\resume\\"+uploadForm.getName()+".doc");
	      
	      /*//Instantiating Tika facade class
	      Tika tika = new Tika();
	      String filecontent = tika.parseToString(file);
	      System.out.println("Extracted Content: " + filecontent);
			//Language detection
	      LanguageIdentifier identifier = new LanguageIdentifier("D:\\resume\\"+uploadForm.getName()+".html ");
	      String language = identifier.getLanguage();
	      System.out.println("Language of the given content is : " + language);*/
		
		
		 //parse method parameters
		   AutoDetectParser parser = new AutoDetectParser();
		   BodyContentHandler handler = new  BodyContentHandler(new ToXMLContentHandler());
		   Metadata metadata = new Metadata();
		   FileInputStream inputstream = new FileInputStream(file);
		   ParseContext context = new ParseContext();
		   //parsing the file
	
		   parser.parse(inputstream, handler, metadata, context);
		   String html = handler.toString();
		   //System.out.println(html);
		   Document doc = Jsoup.parse(html);
		   System.out.println("File content : " + handler);
		   System.out.println("Metadata Is: " + metadata);
		   System.out.println("Context Is: " + context);
		   System.out.println("Get font" +doc.getElementsContainingOwnText("career objective"));
		   
		   LanguageIdentifier identifier = new LanguageIdentifier(
					handler.toString());
			System.out.println("Language is: "+identifier.getLanguage());
		   
	   }	
		catch(TikaException | IOException | SAXException e){
			e.printStackTrace();
		}
		
		//Identifying in bold, italics, underline using POI in doc format
		try{
			FileInputStream fis = new FileInputStream(
					   new File("D:\\resume\\"+uploadForm.getName()+".doc"));
			POIFSFileSystem fs = new POIFSFileSystem( fis );
			HWPFDocument d = new HWPFDocument(fs);
			//XWPFDocument d = new XWPFDocument(fs);
			Range range = d.getRange();
			for(int i = 0; i<range.numCharacterRuns(); i++)
	        {
				CharacterRun cr = range.getCharacterRun(i);
	            if(cr.isItalic()){
	            System.out.println("Italic Character Found\n" +cr);
	            }
	        }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//Identifying in bold, italics, underline using POI in Xls format
		/*try{
			
			 FileInputStream fis = new FileInputStream(
					   new File("D:\\resume\\"+uploadForm.getName()+".xls"));
			 
			 POIFSFileSystem fs = new POIFSFileSystem( fis );
			 HSSFWorkbook wb = new HSSFWorkbook(fs);
			 HSSFSheet sheet = wb.getSheetAt(0);
			 HSSFRow row=sheet.getRow(0);
			 @SuppressWarnings("deprecation")
			 HSSFCell cell=row.getCell((short)0);


			 HSSFRichTextString rts=cell.getRichStringCellValue();
			 System.out.println("---------------------------------------------"+rts); 
			 
		}
		catch(Exception e){
		e.printStackTrace();
		}*/
		
		return null;
	}
}



