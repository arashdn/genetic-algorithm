package genetic_algorithm;

/**
 *
 * @author Arash
 */
public class Main 
{
    
    
    
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new MainForm().setVisible(true);
            }
        });
    }
    
    
//    public static void main(String[] args) 
//    {
//        Polynomial pn = new Polynomial();
//        pn.add(5.0, 2);
//        pn.add(12.0, 1);
//        pn.add(-3.0, 5);
//        pn.add(12.0, 3);
//        pn.add(7.0, 2);
//        pn.add(13.0, 3);
//        pn.add(-9.0, 4);
//        
//        Chromosome.setPolynomial(pn);
//
//        Gene.maxValue = 100;
//        Gene.minValue = -100;
//        Gene.points = 1;
//        
//        int generations = 40;
//        final int types = 8;
//        
//        Chromosome.setCrossOverType(Chromosome.CO_TYPE_DOUBLE_POINT);
//        GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_ROULETTE);
//        GeneticAlgorithm.setAddExisting(false);
//        
//        GeneticAlgorithm ga;
//        //System.out.println(ga.toString());
//        
//        double [][] best = new double[types][generations];
//        double [][] avg = new double[types][generations];
//        String [] names = new String[types];
//        
//        for (int j = 0; j < types; j++)
//        {
//            switch(j)
//            {
//                case 0:
//                    Chromosome.setCrossOverType(Chromosome.CO_TYPE_SINGLE_POINT);
//                    GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_TOURNAMENT);
//                    GeneticAlgorithm.setAddExisting(true);
//                    names[j] = "Single CO - Tournament - Allow Repetitive";
//                    break;
//                case 1:
//                    Chromosome.setCrossOverType(Chromosome.CO_TYPE_SINGLE_POINT);
//                    GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_TOURNAMENT);
//                    GeneticAlgorithm.setAddExisting(false);
//                    names[j] = "Single CO - Tournament - Not Repetitive";
//                    break;
//                case 2:
//                    Chromosome.setCrossOverType(Chromosome.CO_TYPE_SINGLE_POINT);
//                    GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_ROULETTE);
//                    GeneticAlgorithm.setAddExisting(true);
//                    names[j] = "Single CO - Roulete - Allow Repetitive";
//                    break;
//                case 3:
//                    Chromosome.setCrossOverType(Chromosome.CO_TYPE_SINGLE_POINT);
//                    GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_ROULETTE);
//                    GeneticAlgorithm.setAddExisting(false);
//                    names[j] = "Single CO - Roulete - NOT Repetitive";
//                    break;
//                case 4:
//                    Chromosome.setCrossOverType(Chromosome.CO_TYPE_DOUBLE_POINT);
//                    GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_TOURNAMENT);
//                    GeneticAlgorithm.setAddExisting(true);
//                    names[j] = "Double CO - Tournament - Allow Repetitive";
//                    break;
//                case 5:
//                    Chromosome.setCrossOverType(Chromosome.CO_TYPE_DOUBLE_POINT);
//                    GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_TOURNAMENT);
//                    GeneticAlgorithm.setAddExisting(false);
//                    names[j] = "Double CO - Tournament - Not Repetitive";
//                    break;
//                case 6:
//                    Chromosome.setCrossOverType(Chromosome.CO_TYPE_DOUBLE_POINT);
//                    GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_ROULETTE);
//                    GeneticAlgorithm.setAddExisting(true);
//                    names[j] = "Double CO - Roulete - Allow Repetitive";
//                    break;
//                case 7:
//                    Chromosome.setCrossOverType(Chromosome.CO_TYPE_DOUBLE_POINT);
//                    GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_ROULETTE);
//                    GeneticAlgorithm.setAddExisting(false);
//                    names[j] = "Double CO - Roulete - NOT Repetitive";
//                    break;
//            }
//            
//            ga = new GeneticAlgorithm(3000, 75, 20, 1, 30);
//            
//            for (int i = 0; i < generations; i++)
//            {
//                ga.repeat();
//                best[j][i] = ga.getBestChromosome().getFitness();
//                avg[j][i] = ga.getFitnessAverage();
//            }
//        }
//        
//        
//        
//        
//        
//        /////////////////GUI//////////////////
//        JFrame frame = new JFrame("Chart for Best Fitness");
//        final JFXPanel fxPanel = new JFXPanel();
//        frame.add(fxPanel);
//        frame.setSize(800, 630);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        
//        
//        JFrame frame2 = new JFrame("Chart for Average Fitness");
//        final JFXPanel fxPanel2 = new JFXPanel();
//        frame2.add(fxPanel2);
//        frame2.setSize(800, 630);
//        frame2.setVisible(true);
//        frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//
//
//
//        Platform.runLater(new Runnable() 
//        {
//            @Override
//            public void run() 
//            {
//                Scene scene = createChart(best,names);
//                fxPanel.setScene(scene);
//                Scene scene2 = createChart(avg,names);
//                fxPanel2.setScene(scene2);
//            }
//        });
//        
//    }
    
    
//    private static XYChart.Data getChartData(double x, String y) 
//    {
//        XYChart.Data data = new XYChart.Data<>();
//        data.setYValue(x);
//        data.setXValue(y);
//        return data;
//    }
//    
//    private static Scene createChart(double [][] inp , String [] names)
//    {
//        Group  root  =  new  Group();
//        Scene  scene  =  new  Scene(root, javafx.scene.paint.Color.ALICEBLUE);
//
//
//        NumberAxis lineYAxis= new NumberAxis();
//        CategoryAxis lineXAxis = new CategoryAxis();
//        lineYAxis.setLabel("Fitness");
//        lineXAxis.setLabel("Generation");
//        LineChart barChart = new LineChart(lineXAxis,lineYAxis);
//        barChart.setMinSize(800, 600);
//        barChart.setCreateSymbols(false);
//
//        XYChart.Series [] bar = new XYChart.Series[names.length];
//        
//        for (int j = 0; j < names.length; j++)
//        {
//            bar[j] = new XYChart.Series<>();
//            bar[j].setName(names[j]);
//
//            for (int i = 0; i < inp[j].length ; i++) 
//            {
//                bar[j].getData().add(getChartData(inp[j][i], ""+(i+1)));
//            }
//            barChart.getData().addAll(bar[j]);
//        }
//        
//        
//        root.getChildren().add(barChart);
//
//        return (scene);
//    }
    
}
