/**
 * 
 */
package resumeBuilder.service;

import org.springframework.web.multipart.MultipartFile;

import resumeBuilder.domain.FileUploadForm;

/**
 * @author ChandniJ
 *
 */

public interface IResumeBuilderService {

	MultipartFile tikaExtracter(FileUploadForm uploadForm);
}
