package example;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.StreamingFileUpload;
import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Controller
public class UploadController {
    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);

    @Post(value = "/file", consumes = MediaType.MULTIPART_FORM_DATA)
    public HttpResponse upload(StreamingFileUpload file) throws IOException {
        Flowable.fromPublisher(file)
                .map(partData -> {
                    return partData.getByteBuffer();
                })
                .subscribe(byteBuffer -> {
                    LOG.info("byteBuffer: " + byteBuffer.position());
                });

        return HttpResponse.ok();
    }

}
