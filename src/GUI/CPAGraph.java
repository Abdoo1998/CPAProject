package GUI;

import com.mxgraph.view.mxGraph;

/**
 * Extension of mxGraph.
 *
 * @author gorosgobe
 */
public class CPAGraph extends mxGraph {

    //Prevents edges from being selectable
    @Override
    public boolean isCellSelectable(Object cell) {
        if (model.isEdge(cell))
        {
            return false;
        }

        return super.isCellSelectable(cell);
    }
}
