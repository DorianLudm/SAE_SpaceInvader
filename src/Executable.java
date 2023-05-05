import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;


public class Executable extends Application {
    private Pane root;
    private Group caracteres;
    private GestionJeu gestionnaire;
    private int hauteurTexte;
    private int largeurCaractere;
    private boolean espaceOn =false;
    private boolean droiteOn=false;
    private boolean gaucheOn=false;
    private boolean rOn=false;
    public static void main(String[] args) {
        launch(args);
    }

    Thread espace = new Thread(){
        public void run(){
            if(espaceOn){
                gestionnaire.toucheEspace();
            }
        }
    };

    Thread droite = new Thread(){
        public void run(){
            if(droiteOn){
                gestionnaire.toucheDroite();
            }
        }
    };

    Thread gauche = new Thread(){
        public void run(){
            if(gaucheOn){
                gestionnaire.toucheGauche();
            }
        }
    };

    Thread r = new Thread(){
        public void run(){
            if(rOn){
                gestionnaire.toucheR();
            }
        }
    };

    private void afficherCaracteres(){
        caracteres.getChildren().clear();
        int hauteur = (int) root.getHeight();
        for( ChainePositionnee c : gestionnaire.getChaines().chaines)
        {
            Text t = new Text (c.x*largeurCaractere,hauteur - c.y*hauteurTexte, c.c);
            t.setFont(Font.font ("Monospaced", 10));
            caracteres.getChildren().add(t);
        }
    }

    private void lancerAnimation() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                    new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent actionEvent) {
                            gestionnaire.jouerUnTour();
                            afficherCaracteres();
                            r.run();
                            espace.run();
                            droite.run();
                            gauche.run();
                        }
                    }),
                new KeyFrame(Duration.seconds(0.025))
                );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("IUTO Space Invader");
            caracteres = new Group();
            root= new AnchorPane(caracteres);
            gestionnaire = new GestionJeu();
            Text t=new Text("█");
            t.setFont(Font.font("Monospaced",10));
            hauteurTexte =(int) t.getLayoutBounds().getHeight();
            largeurCaractere = (int) t.getLayoutBounds().getWidth();

            Scene scene = new Scene(root,gestionnaire.getLargeur()*largeurCaractere,gestionnaire.getHauteur()*hauteurTexte);
            scene.setOnKeyPressed((key) -> {
                if(key.getCode()==KeyCode.LEFT)
                    this.gaucheOn = true;
                if(key.getCode()==KeyCode.RIGHT)
                    this.droiteOn = true;
                if(key.getCode()==KeyCode.SPACE)
                    this.espaceOn = true;
                if(key.getCode()==KeyCode.ESCAPE)
                    gestionnaire.toucheEscape();
                if(key.getCode()==KeyCode.R)
                    this.rOn = true;
            });

            scene.setOnKeyReleased((key) -> {
                if(key.getCode()==KeyCode.LEFT)
                    this.gaucheOn = false;
                if(key.getCode()==KeyCode.RIGHT)
                    this.droiteOn = false;
                if(key.getCode()==KeyCode.SPACE)
                    this.espaceOn = false;
                if(key.getCode()==KeyCode.ESCAPE)
                    gestionnaire.toucheEscape();
                if(key.getCode()==KeyCode.R)
                    this.rOn = false;
            });
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            lancerAnimation();

        }
}
