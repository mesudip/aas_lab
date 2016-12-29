package rendercore;

import gcore.Object;


/**
 * @author sudip
 * This class is for applying proper viewport Transform.
 * Though the camera is same, we might want to duplicate the view.
 * This class does so without having to restart the prospective
 * This class'importance comes when window is being resized. 
 * In such case, output of perspective transform is same and
 * previous output can be used and simply be rescaled.
 *
 */
public class ViewPortRenderer extends Object {

}
