/**
 * 
 */
package resumeBuilder.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import resumeBuilder.domain.FileUploadForm;
import resumeBuilder.service.ResumeBuilderservice;

/**
 * @author ChandniJ
 *
 */
@RestController
public class FileUploadController {
	
	@Autowired
	ResumeBuilderservice resumeBuilderService;

	
	@RequestMapping(value = "/upload", method=RequestMethod.GET)
	public ModelAndView getUploadpage() {
		ModelAndView model=new ModelAndView("index");
		return model;
	}
	
	 /*@RequestMapping(value="/upload", method=RequestMethod.GET)
	    public @ResponseBody String provideUploadInfo() {
		 return new ModelAndView("index");
	    }*/

	    @RequestMapping(value="/upload", method=RequestMethod.POST)
	    public ModelAndView save(@ModelAttribute("uploadForm") FileUploadForm uploadForm,BindingResult result,Model model) {
	    	if(result.hasErrors()){
	    		model.addAttribute("upload",uploadForm);
				return new ModelAndView("upload");
	    	}
	    	else{
	    		MultipartFile multipartFile = resumeBuilderService.tikaExtracter(uploadForm);
	    		ModelAndView model1 = new ModelAndView("index");
	    		model1.addObject("resumeView", multipartFile);
	    		return model1;
	    }
	    }
}
	    	   /*MultipartFile multipartFile = uploadForm.getFile();
	   		if (multipartFile != null) {

	   			try {
	                byte[] bytes = multipartFile.getBytes();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(new File("D:\\resume\\"+uploadForm.getName()+".doc")));
	                stream.write(bytes);
	                stream.close();
	                return "You successfully uploaded" +uploadForm.getName();
	            } catch (Exception e) {
	                return "You failed to upload" +uploadForm.getName();
	            }
	        } else {
	            return "You failed to upload because the file was empty.";
	        }
	    }
	    }
	    /*
	    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
	            @RequestParam("file") MultipartFile file){
	        if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(new File("D:\\resume\\" + name + "-uploaded")));
	                stream.write(bytes);
	                stream.close();
	                return "You successfully uploaded " + name + "!";
	            } catch (Exception e) {
	                return "You failed to upload " + name + " => " + e.getMessage();
	            }
	        } else {
	            return "You failed to upload " + name + " because the file was empty.";
	        }
	    }

}
*/