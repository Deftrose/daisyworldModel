import java.io.FileWriter;
import java.io.IOException;

/**
 * Main Class bring each component together and print out the result of experiment as cvs file
 *
 * Use the preSetting method to preset some parameters and the main method would run the experiment and print it.
 */
public class Main{

    public static void main(String []args ) {

        // the MAX_TIME const would define how many time periods would this model
        // run in an experiment.
        final int MAX_TIME = 500;

        // Set the parameters for exp1
        paraSetting(0.5,0.5,1.4,0.4,0.25,0.75);
        do_exp("exp1", MAX_TIME);

        // Set the parameters for exp2
        paraSetting(0.5,0.5,1,0.4,0.25,0.75);
        do_exp("exp2", MAX_TIME);

        // Set the parameters for exp3
        paraSetting(0.5,0.5,0.6,0.4,0.25,0.75);
        do_exp("exp3", MAX_TIME);
    }

    /**
     * This method is used to preset the parameters. It could also been done manually.
     * However, that would require to change the value, run and repeat. This method would
     * make this process done multiple times with running this program once.
     *
     * In this method, the solar luminosity should be a fixed value
     * if need to get a experiment of changing solar luminosity
     * use method changingSolarSetting
     */
    private static void paraSetting(double BLACK_DAISY, double WHIT_DAISY, double LUMINOSITY,
                             double SUR_ALBEDO, double BLK_ALBEDO, double WHITE_ALBEDO ){
        Params.BLACK_DAISY = BLACK_DAISY;
        Params.WHITE_DAISY = WHIT_DAISY;
        Params.SUR_ALBEDO = SUR_ALBEDO;
        Params.BLACK_ALBEDO = BLK_ALBEDO;
        Params.WHITE_ALBEDO = WHITE_ALBEDO;
        Params.SOLAR_LUMINOSITY = LUMINOSITY;
    }

    /**
     * This method is only used when you need to model a changing solar luminosity
     * This param LUMINOSITY is only the initial Luminosity and would change through the time
     * @param LUMINOSITY
     */
    private static void changingSolarSetting(double BLACK_DAISY, double WHIT_DAISY, double LUMINOSITY,
                             double SUR_ALBEDO, double BLK_ALBEDO, double WHITE_ALBEDO ){
        Params.BLACK_DAISY = BLACK_DAISY;
        Params.WHITE_DAISY = WHIT_DAISY;
        Params.SUR_ALBEDO = SUR_ALBEDO;
        Params.BLACK_ALBEDO = BLK_ALBEDO;
        Params.WHITE_ALBEDO = WHITE_ALBEDO;
        Params.SOLAR_LUMINOSITY = LUMINOSITY;
    }

    /**
     *  This method is used to do the experiments and print the data in csv files.
     */
    private static void do_exp(String expNum, int MAX_TIME){

        World daisyWorld = new World();

        try{
            FileWriter temp_writer = new FileWriter(expNum + "Temp_result"+".csv");
            FileWriter daisy_writer = new FileWriter(expNum +"Daisy_result"+".csv");
            temp_writer.append("ticks" + "," +"Temperature"+"\n");
            daisy_writer.append("ticks" + "," +"BlackDaisy"+","+"WhiteDaisy"+"\n");
            for(int i=0 ; i< MAX_TIME ; i++ ){
                daisyWorld.tick();
                temp_writer.append(i + "," );
                temp_writer.append(String.valueOf(daisyWorld.getGlobalTemp()) + "," +"\n");
                daisy_writer.append(i + "," );
                daisy_writer.append(String.valueOf(daisyWorld.getBlack_num() + "," ));
                daisy_writer.append(String.valueOf(daisyWorld.getWhite_num() + "," +"\n"));
            }
            daisy_writer.close();
            temp_writer.close();
        } catch (IOException e){
            System.out.println("got an IOException" + e);
        }
    }
}