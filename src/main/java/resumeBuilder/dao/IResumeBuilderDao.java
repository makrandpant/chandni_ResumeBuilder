package resumeBuilder.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import resumeBuilder.domain.FileUploadForm;

public interface IResumeBuilderDao {

	MultipartFile tikaExtracter(FileUploadForm uploadForm);
}



