package litecart.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.Resource;

import java.io.File;

@UtilityClass
public class FileUtils {

    @SneakyThrows
    public static File getResourceAsFile(Resource resource) {
        return resource.getFile();
    }
}
