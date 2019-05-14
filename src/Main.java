import java.io.FileWriter;
import java.io.IOException;

public class Main{

    public static void main(String []args ) {

        World daisyWorld = new World();

        try{
            FileWriter writer = new FileWriter("result.csv");
            for(int i=0 ; i< 400 ; i++ ){
                daisyWorld.tick();
                writer.append(String.valueOf(daisyWorld.globalTemp + "," +"\n"));
            }

            writer.close();
        } catch (IOException e){

        }

    }
}