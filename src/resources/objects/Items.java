package resources.objects;

import javax.swing.*;
import java.awt.*;

public abstract class Items {

    protected Icon icon;

    public void setIcon(String path) {
        this.icon = new ImageIcon(path);
    }

    public Icon getPicture() {
        return icon;
    }

}
