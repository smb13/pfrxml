package ru.mshamanin.pfrxml.web.pack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.mshamanin.pfrxml.util.PackUtil;
import ru.mshamanin.pfrxml.web.FileModel;
import ru.mshamanin.pfrxml.web.PackValidator;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/uploading")
public class PackUploadController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final ServletContext context;
    private final PackValidator fileValidator;

    public PackUploadController(ServletContext context, PackValidator fileValidator) {
        this.context = context;
        this.fileValidator = fileValidator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView fileUploadPage() {
        FileModel file = new FileModel();
        return new ModelAndView("uploading", "command", file);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String fileUpload(@Validated FileModel file, BindingResult result, ModelMap model) throws IOException {
        MultipartFile multipartFile = file.getFile();
        fileValidator.validate(file, result);
        if (result.hasErrors()) {
            log.warn("validation file errors");
            return "pfrxml";
        } else {
            log.info("Fetching file");
            String uploadPath = context.getRealPath("") + File.separator + "temp" + File.separator;
            File uploadedFile = new File(uploadPath + file.getFile().getOriginalFilename());
            File uploadedFolder = new File(uploadPath);
            if (!uploadedFolder.exists()) {
                if (!uploadedFolder.mkdirs()) {
                    log.warn("can not create temporary folder {}", uploadedFolder.getCanonicalPath());
                    return "pfrxml";
                }
            }
            if (uploadedFile.exists()) {
                if (uploadedFile.delete()) {
                    log.info("old file {} deleted", uploadedFile.getCanonicalPath());
                } else {
                    log.warn("error deletion file {}", uploadedFile.getCanonicalPath());
                    return "pfrxml";
                }
            } else {
                log.warn("old file with the same name as uploading not found on server");
            }
            if (!uploadedFile.createNewFile()) {
                log.warn("can not create file on server to upload");
                return "pfrxml";
            } else {
                log.info("file {} created on server", uploadedFile.getCanonicalPath());
            }
            FileCopyUtils.copy(file.getFile().getBytes(), uploadedFile);
            log.info("file {} uploaded on server", uploadedFile.getCanonicalPath());
            PackUtil.unzipPack(uploadedFile);
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            log.info("Fetching file finished successfully");
            return "success";
        }
    }
}
