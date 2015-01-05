package it.dindonkey.gameoflife;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class GameOfLifeActivity extends Activity
{

    private WorldView worldView;
    private Button nextStepButton;
    private Button playButton;
    private Thread playThread;
    private boolean shouldContinue;
    private Spinner patternSpinner;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        worldView = ((WorldView) findViewById(R.id.world));
        worldView.init(15, 15);

        nextStepButton = ((Button) findViewById(R.id.stepButton));
        nextStepButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                worldEvolve();
            }
        });

        playButton = (Button) findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(getString(R.string.play).equals(playButton.getText()))
                {
                    playButton.setText(getString(R.string.stop));
                    nextStepButton.setEnabled(false);
                    shouldContinue = true;
                    playThread = new Thread(){
                        @Override
                        public void run()
                        {
                            while(shouldContinue)
                            {

                                try
                                {
                                    worldView.world.evolveToNextGeneration();
                                    worldView.postInvalidate();
                                    Thread.sleep(200);
                                }catch (InterruptedException e)
                                {
                                    shouldContinue = false;
                                }


                            }
                        }
                    };
                    playThread.start();
                }
                else
                {
                    playButton.setText(getString(R.string.play));
                    nextStepButton.setEnabled(true);
                    shouldContinue = false;

                }
            }
        });

        patternSpinner = (Spinner) findViewById(R.id.patternSpinner);
        GolPattern[] patterns = new GolPattern[] {
                new GolPattern("Blank (15x15)", new int[15][15]),
                new GolPattern("Pulsar",new int[][]{
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,1,1,1,0,0,0,1,1,1,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,1,0,0,0,0,1,0,1,0,0,0,0,1,0},
                        {0,1,0,0,0,0,1,0,1,0,0,0,0,1,0},
                        {0,1,0,0,0,0,1,0,1,0,0,0,0,1,0},
                        {0,0,0,1,1,1,0,0,0,1,1,1,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,1,1,1,0,0,0,1,1,1,0,0,0},
                        {0,1,0,0,0,0,1,0,1,0,0,0,0,1,0},
                        {0,1,0,0,0,0,1,0,1,0,0,0,0,1,0},
                        {0,1,0,0,0,0,1,0,1,0,0,0,0,1,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,1,1,1,0,0,0,1,1,1,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                })
        };
        ArrayAdapter<GolPattern> adapter = new ArrayAdapter<GolPattern>(this,android.R.layout.simple_spinner_item,patterns);
        patternSpinner.setAdapter(adapter);
        patternSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                GolPattern itemSelected = (GolPattern)parent.getItemAtPosition(position);
                worldView.world.currentGeneration = itemSelected.matrix;
                worldView.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

    }

    private void worldEvolve()
    {
        worldView.world.evolveToNextGeneration();
        worldView.invalidate();
    }


}
