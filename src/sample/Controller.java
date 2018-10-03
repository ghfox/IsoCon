package sample;

import javafx.event.ActionEvent;
import javafx.geometry.Point3D;
import javafx.scene.control.TextField;

import java.lang.Math;

public class Controller {

    public TextField TileWidth;
    public TextField IsoX;
    public TextField IsoY;
    public TextField TileHeight;
    public TextField Z;
    public TextField X;
    public TextField Y;

    public final double defTileHeight = Math.cos(Math.toRadians(26.565));
    public final double defTileWidth = Math.sin(Math.toRadians(45));

    public void isoToXYZ(ActionEvent actionEvent) {
        Point3D point = convertFromIso(
                enterAsDouble(IsoX, 0.0),
                enterAsDouble(IsoY, 0.0),
                enterAsDouble(Z,0.0),
                enterAsDouble(TileWidth, defTileWidth),
                enterAsDouble(TileHeight, defTileHeight)
        );
        X.setText("" + point.getX());
        Y.setText("" + point.getY());
    }

    public Point3D convertFromIso(double isoX, double isoY, double z, double tileWidth, double tileHeight)
    {
        isoY += z * tileHeight;
        isoX = isoX / tileWidth;
        isoY = isoY / tileWidth;
        double m_x = ( (2 * isoY) + isoX ) * 0.5;
        double m_y = ( (2 * isoY) - isoX ) * 0.5;
        System.out.println("X:" + m_x + ", Y:" + m_y + ", Z:" + z);
        return new Point3D(m_x, m_y, z);
    }

    public double enterAsDouble(TextField tf, double def)
    {
        double newDbl = def;
        String usrVal = tf.getText();
        try {
            newDbl = Double.parseDouble(usrVal);
        } catch (Exception e) {
            System.out.println("Error : Not a number : " + usrVal);
        }
        return newDbl;
    }

    public void setDefaults()
    {
        TileHeight.setText("" + defTileHeight);
        TileWidth.setText("" + defTileWidth);
        IsoX.setText("0.0");
        IsoY.setText("0.0");
        Z.setText("0.0");
        X.setText("0.0");
        Y.setText("0.0");
    }

    public void setDefaults(ActionEvent actionEvent) {
        setDefaults();
    }

    public void initialize(){
        setDefaults();
    }
}
