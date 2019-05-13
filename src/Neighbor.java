import java.util.ArrayList;
import java.util.Random;

/**
    This Neighbor Class provides methods to get neighbor patches according to a patchs' location
    according to the patch's location
 */
public class Neighbor {

    /**
     * get an array of one patches' neighbor patches
     * @param location_x
     * @param location_y
     * @param patches
     * @return neighborArray
     */
    static Patch[] getNeighbors(int location_x, int location_y, Patch[][] patches){
        ArrayList<Patch> neighborPatches = new ArrayList<Patch>();
        for(int x = location_x-1 ; x <= location_x+1 ; x++){
            for(int y = location_y-1 ; y<= location_y+1 ; y++){
                boolean ex_condition = ( x<0 || x>= Params.MAX_X ||
                                        y<0 || y>=Params.MAX_Y || ( x== location_x && y== location_y));
                if(!ex_condition){
                    neighborPatches.add(patches[x][y]);
                }
            }
        }
        Patch[] neighborArray = new Patch[neighborPatches.size()];
        neighborPatches.toArray(neighborArray);
        return neighborArray;
    }

    /**
     * Randomly pick an empty patch from its neighbors, if there is no empty patches
     * it would return null
     * @param location_x
     * @param location_y
     * @param patches
     * @return randomEmptyPatch
     */
    static Patch getRandomEmptyNeighbor(int location_x, int location_y, Patch[][] patches){

        Patch[] neighbors = getNeighbors(location_x,location_y,patches);
        ArrayList<Patch> emptyPatches = new ArrayList<Patch>();
        Random random = new Random();

        for(int i=0 ; i< neighbors.length ; i++){
            if(neighbors[i].getDaisy() == null)
                emptyPatches.add(neighbors[i]);
        }

        if(emptyPatches.size() == 0 )
            return null;
        else
            return emptyPatches.get(random.nextInt(emptyPatches.size()));
    }
}
