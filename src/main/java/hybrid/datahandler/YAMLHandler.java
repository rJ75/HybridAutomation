package hybrid.datahandler;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class YAMLHandler {

	private String filePath;
	private Object object;

    public YAMLHandler(String filePath, Object object) {
        this.filePath = filePath;
        this.object = object;
    }

    public Object readYaml() {
    	Constructor constructor = new Constructor(object.getClass());
		Yaml yaml = new Yaml( constructor );
        try {
        	object =  yaml.loadAs(new FileReader(filePath), object.getClass());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return object;
    }
}
