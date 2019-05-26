/**
 *  This is the daisy class who has its color and age
 *  Black and white daisy would have different albedo
 *
 */
public class Daisy {

    public enum Color{
        BLACK,
        WHITE,
        YELLOW
    }

    // define the color of daisy
    private Color DaisyColor;

    // define the age of daisy
    private int age;

    // the albedo of this daisy
    private double albedo;

    // Constructor
    public Daisy(Color daisyColor, int age) {
        DaisyColor = daisyColor;
        /*if( daisyColor == Color.BLACK){
            this.albedo = Params.BLACK_ALBEDO;
        }else{
            this.albedo = Params.WHITE_ALBEDO;
        }*/
        switch ( daisyColor ){
            case BLACK:
                this.albedo = Params.BLACK_ALBEDO; break;
            case WHITE:
                this.albedo = Params.WHITE_ALBEDO; break;
            case YELLOW:
                this.albedo = Params.YELLOW_ALBEDO; break;
        }
        this.age = age;
    }

    // getter and setter
    public Color getDaisyColor() {
        return DaisyColor;
    }

    public void setDaisyColor(Color daisyColor) {
        DaisyColor = daisyColor;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getAlbedo() {
        return albedo;
    }

    public void setAlbedo(double albedo) {
        this.albedo = albedo;
    }
}
