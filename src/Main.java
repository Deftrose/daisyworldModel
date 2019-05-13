import java.util.Random;

public class Main{

    public static void main(String []args ) {

         double globalTemp = 0.0;

        /*
            Initialise the system state and load the system initial properties
         */
        Patch[][] patches = new Patch[Params.MAX_X][Params.MAX_Y];

        init(patches);

        /*
        for(int i=0 ; i<10 ; i++){
            for(int m =0; m<10 ;m++){
                System.out.println(patches[i][m].getDaisy().getDaisyColor());
            }
        }*/

        // Calculate the temperature of each patch
        for(int x=0; x< Params.MAX_X ; x++ ){
            for(int y=0 ; y< Params.MAX_Y ; y++){
                patches[x][y].calTeperatrue();
            }
        }

        // Calculate the temperature of each patch after diffusion
        for(int x=0; x< Params.MAX_X; x++){
            for(int y=0; y< Params.MAX_Y; y++){
                diffuse(x,y,patches);
            }
        }

        // Set the global temperature
        globalTemp = setGlobalTemper(patches);

        System.out.println(globalTemp);

    }

    // Use this method to initialise the daisy world
    // All the patches be created and plant the daisies according to the settings
    private static void init(Patch[][] patches){
        Random random = new Random();
        int temp_x,temp_y;
        Patch tempPatch ;

        for(int i=0 ; i< Params.MAX_X ; i++){
            for(int m=0 ; m< Params.MAX_Y ; m++){
                patches[i][m] = new Patch(null, Params.INIT_TEMP);
            }
        }

        // plant the white daisies
        for(int i =0; i < (Params.WHITE_DAISY * Params.MAX_X * Params.MAX_Y ) ; i++ ){
            // only plant daisies where there is no daisy
            do{
                temp_x = random.nextInt(Params.MAX_X);
                temp_y = random.nextInt(Params.MAX_Y);
                tempPatch = patches[temp_x][temp_y];
            }while(tempPatch.getDaisy() != null);
            patches[temp_x][temp_y].setDaisy(new Daisy(Daisy.Color.WHITE,random.nextInt(Params.AGE_MAX)));
        }

        // plant the black daisies
        for(int i =0; i< (Params.BLACK_DAISY * Params.MAX_X * Params.MAX_Y ) ; i++){
            // only plant daisies where there is no daisy
            do{
                temp_x = random.nextInt(Params.MAX_X);
                temp_y = random.nextInt(Params.MAX_Y);
                tempPatch = patches[temp_x][temp_y];
            }while(tempPatch.getDaisy() != null);
            patches[temp_x][temp_y].setDaisy(new Daisy(Daisy.Color.BLACK,random.nextInt(Params.AGE_MAX)));
        }
    }

    // In each tick, each patch would diffuse the heat to its neighbors
    private static void diffuse(int location_x, int location_y, Patch[][] patches ){

        // The patch would gain energy from its neighbors
        for(int x = location_x-1; x< location_x+1 ; x++){
            for( int y= location_y-1; y< location_y+1 ; y++){
                // The patches out of border and the patch itself would be excluded
                boolean ex_condition = (x<0 || x > Params.MAX_X
                                    || y<0 || y > Params.MAX_Y
                                    || (x == location_x && y == location_y ));
                if(!ex_condition){
                    // the energy would be absorbed by this one
                    patches[location_x][location_y].setTemperature( patches[x][y].getTemperature()/16 );
                    // the neighbor would give out 1/16 of its energy
                    patches[x][y].setTemperature( patches[x][y].getTemperature()* 15 /16 );
                }
            }
        }
    }

    private static double setGlobalTemper(Patch[][] patches){
        double tempSum = 0;

        for(int x=0; x< Params.MAX_X ;x++){
            for(int y =0; y< Params.MAX_Y ;y++){
                tempSum +=patches[x][y].getTemperature();
            }
        }

        return tempSum/(Params.MAX_Y * Params.MAX_X);
    }
}