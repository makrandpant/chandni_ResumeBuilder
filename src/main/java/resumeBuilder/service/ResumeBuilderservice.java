/**
 * 
 */
package resumeBuilder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import resumeBuilder.dao.IResumeBuilderDao;

import resumeBuilder.domain.FileUploadForm;

/**
 * @author ChandniJ
 *
 */
@Service
public class ResumeBuilderservice implements IResumeBuilderService {

	@Autowired
	IResumeBuilderDao connect;
	
	@Override
	public MultipartFile tikaExtracter(FileUploadForm uploadForm) {
		// TODO Auto-generated method stub
		return connect.tikaExtracter(uploadForm);
	}

}
