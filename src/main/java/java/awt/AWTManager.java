package java.awt;

import org.mini.gui.GCallBack;
import org.mini.gui.GForm;
import org.mini.gui.GObject;

import java.util.Arrays;

public class AWTManager {

    static public interface AwtComponentProcessor {
        void process(Component f);
    }


    static public void iterAwtComponentAndProcess(AwtComponentProcessor processor) {
        GForm gForm = GCallBack.getInstance().getApplication().getForm();
        if (gForm != null) {
            GObject[] objs = new GObject[gForm.getElements().size()];
            gForm.getElements().toArray(objs);
            for (int i = 0; i < objs.length; i++) {
                GObject gObject = objs[i];
                Object obj = gObject.getAttachment();
                if (obj != null && obj instanceof Component) {
                    Component comp = (Component) obj;
                    deepProcess(comp, processor);
                }
            }
//    this would throw concurrent exception
//            gForm.getElements().forEach(gObject -> {
//                Object obj = gObject.getAttachment();
//                if (obj != null && obj instanceof Component) {
//                    Component comp = (Component) obj;
//                    deepProcess(comp, processor);
//                }
//            });
        }
    }

    static private void deepProcess(Component comp, AwtComponentProcessor processor) {
        if (comp instanceof Container) {
            Container f = (Container) comp;
            processor.process(f);
            f.getChildren().forEach(son -> {
                deepProcess(son, processor);
            });
        } else {
            processor.process(comp);
        }
    }

}
