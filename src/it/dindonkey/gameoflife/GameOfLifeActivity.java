package it.dindonkey.gameoflife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStreamReader;

public class GameOfLifeActivity extends Activity
{

    private WorldView worldView;
    private Button nextStepButton;
    private Button playButton;
    private Thread playThread;
    private boolean isPlaying;
    private EditText numCellsEditText;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        worldView = ((WorldView) findViewById(R.id.world));
        worldView.init(30, 30);

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
                if(!isPlaying)
                {
                    playButton.setText(getString(R.string.stop));
                    nextStepButton.setEnabled(false);
                    isPlaying = true;
                    playThread = new Thread(){
                        @Override
                        public void run()
                        {
                            while(isPlaying)
                            {

                                try
                                {
                                    worldView.world.evolveToNextGeneration();
                                    worldView.postInvalidate();
                                    Thread.sleep(50);
                                }catch (InterruptedException e)
                                {
                                    isPlaying = false;
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
                    isPlaying = false;

                }
            }
        });

        findViewById(R.id.pickAPatternButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(GameOfLifeActivity.this, LifChooserActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        numCellsEditText = (EditText) findViewById(R.id.numCellEditText);
        findViewById(R.id.resetGridButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                worldView.init(Integer.parseInt(numCellsEditText.getText().toString()),Integer.parseInt(numCellsEditText.getText().toString()));
                worldView.invalidate();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        String fileName = data.getStringExtra("fileName");
        try
        {
            LIFFile lifFile = LIFFileReader.fromFile(new InputStreamReader(getAssets().open("gol_patterns/" + fileName)));
            worldView.init(Math.max(lifFile.cols,lifFile.rows), Math.max(lifFile.cols,lifFile.rows));
            worldView.world.drawLifePattern(lifFile.patterns);
            worldView.invalidate();
        } catch (IOException e)
        {
            Toast.makeText(GameOfLifeActivity.this,"File " + fileName + " not found!",Toast.LENGTH_SHORT).show();
        }

    }

    private void worldEvolve()
    {
        worldView.world.evolveToNextGeneration();
        worldView.invalidate();
    }


}
