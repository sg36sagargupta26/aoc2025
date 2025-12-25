package days.day10;
import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;

public class MultiIntegerProgrammingSolver {
    private final IndicatorDetails indicatorDetails;
    private final MPSolver solver;
    private final MPVariable[] mpVariables;
    private final MPObjective mpObjective;
    public MultiIntegerProgrammingSolver(final IndicatorDetails indicatorDetails){
        Loader.loadNativeLibraries();
        this.indicatorDetails = indicatorDetails;
        this.solver = MPSolver.createSolver("SCIP_MIXED_INTEGER_PROGRAMMING");
        mpVariables = variableList();
        constraintList();
        mpObjective = objective();
    }

    private MPObjective objective() {
        MPObjective objective = solver.objective();
        for (MPVariable mpVariable : mpVariables) {
            objective.setCoefficient(mpVariable, 1);
        }
        return objective;
    }

    public int solve(){
        final MPSolver.ResultStatus resultStatus = solver.solve();
        if(resultStatus == MPSolver.ResultStatus.OPTIMAL){
            return (int) mpObjective.value();
        }
        return 0;

    }

    private MPVariable[] variableList(){
        MPVariable[] variables = new MPVariable[indicatorDetails.buttons().size()];
        for(int i=0;i<variables.length;i++){
            variables[i]= solver.makeIntVar(0.0, Double.POSITIVE_INFINITY,"x"+i);
        }
        return variables;
    }

    private void constraintList() {
        MPConstraint[] mpConstraints = new MPConstraint[indicatorDetails.voltages().size()];
        var  buttons = indicatorDetails.buttons();
        int[][] constraints = new int[mpConstraints.length][buttons.size()];
        for(int i=0;i<buttons.size();i++){
            for (var b : buttons.get(i)){
                constraints[b][i]++;
            }
        }
        var voltages = indicatorDetails.voltages();
        for(int i =0;i<mpConstraints.length;i++){
            mpConstraints[i] = solver.makeConstraint(voltages.get(i),voltages.get(i) , "c" +i);
            for(int j =0;j<buttons.size();j++){
                mpConstraints[i].setCoefficient(mpVariables[j],constraints[i][j]);
            }
        }
    }


}
