/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;


import java.util.Random;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * Memory Mind Game
 */
public class JavaFXApplication1 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        /* Start Box containing Player1,Player2 names and selecting grid size*/
        Label lbl = new Label("The Memory Game");
        lbl.setFont(Font.font("Amble CN", FontWeight.BOLD, 24));
        Label label1 = new Label("#Player 1 :");
        TextField textField1 = new TextField ();   
        Label label2 = new Label("#Player 2:");
        TextField textField2 = new TextField ();
        
        Button btn1 = new Button();
        btn1.setText("Start Game");
        GridPane gpX = new GridPane();
        gpX.setAlignment(Pos.CENTER);
       RadioButton rb1 = new RadioButton("6X6");
       RadioButton rb2 = new RadioButton("8X8");
       RadioButton rb3 = new RadioButton("10X10");
        ToggleGroup tg = new ToggleGroup();
       rb1.setToggleGroup(tg);
       rb2.setToggleGroup(tg);
       rb3.setToggleGroup(tg);
       rb1.setSelected(true);
        gpX.setHgap(10);
        gpX.setVgap(25);
        gpX.add(lbl, 2, 0);
        gpX.add(label1, 1 ,2);
        gpX.add(textField1, 2, 2);
        gpX.add(label2, 1 ,3);
        gpX.add(textField2, 2, 3);
        
        
        HBox hbX = new HBox();
        hbX.getChildren().add(rb1);
        hbX.getChildren().add(rb2);
        hbX.getChildren().add(rb3);
        hbX.setSpacing(20);
        hbX.setAlignment(Pos.CENTER);
        HBox hbY = new HBox();
        hbY.getChildren().add(btn1);
        hbY.setAlignment(Pos.CENTER);
        gpX.add(hbX, 2, 4);
        
        gpX.add(hbY, 2, 5);
        String image = JavaFXApplication1.class.getResource("images/Image9.jpg").toExternalForm();
        gpX.setStyle("-fx-background-image: url('" + image + "');-fx-background-position: center center;-fx-background-repeat: stretch;");
        //vb.getChildren().add(btn1);
        Scene scene = new Scene(gpX);
        
        Stage primaryStage = new Stage();  
        primaryStage.setScene(scene);
        primaryStage.setWidth(350);
        primaryStage.setHeight(300);
        
        
        
        /* Event handling on clicking Start Game button */ 
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();               /*Closing Start Game Box */
                
                /*Selecting Theme */
                
                GridPane gpx = new GridPane();
                Image img;
                Image img1 = new Image(getClass().getResourceAsStream("images/back.png"));
                Image img2 = new Image(getClass().getResourceAsStream("images/Image1.jpg"));
                Image img3 = new Image(getClass().getResourceAsStream("images/images2.jpg"));
                Image img4 = new Image(getClass().getResourceAsStream("images/Image3.jpg"));
                Image img5 = new Image(getClass().getResourceAsStream("images/Image4.jpg"));
                ImageView imx = new ImageView(img1);
                gpx.setHgap(10);
                gpx.add(imx, 0, 0);
                imx = new ImageView(img2);
                gpx.add(imx, 1, 0);
                imx = new ImageView(img3);
                gpx.add(imx, 2, 0);
                imx = new ImageView(img4);
                gpx.add(imx, 3, 0);
                imx = new ImageView(img5);
                gpx.add(imx, 4, 0);
                Stage new_stage =  new Stage();
                
               
                gpx.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler(){
                    
      
                     @Override
             public void handle(MouseEvent t){
                  Node target = (Node)t.getTarget();
               // gp.getChildren().remove(target);
                
                int x,y;
               
                x = gpx.getRowIndex(target);
                y = gpx.getColumnIndex(target);
                 
                System.out.println(y);
                new_stage.close();
                String s1 = textField1.getText();
                String s2 = textField2.getText();
                RadioButton chk = (RadioButton)tg.getSelectedToggle();
                String s3 = chk.getText();
                Stage newStage = new Stage();
                Scene sec; 
                switch(y)
                {
                    case 0 : 
                         sec = firstScene(newStage,s1,s2,s3,img1);
                        break;
                    case 1 :
                         sec = firstScene(newStage,s1,s2,s3,img2);
                        break;
                    case 2 : 
                        sec = firstScene(newStage,s1,s2,s3,img3);
                        break;
                    case 3 :
                        sec = firstScene(newStage,s1,s2,s3,img4);
                        break;
                    case 4 :
                        sec = firstScene(newStage,s1,s2,s3,img2);
                    default :
                        sec = firstScene(newStage,s1,s2,s3,img5);
                }
                
                newStage.setScene(sec);
                newStage.setMaximized(true);
                newStage.show(); 
             }
                });
                Scene new_scene = new Scene(gpx);
                new_stage.setTitle("Select your theme");
                new_stage.setScene(new_scene);
                new_stage.show(); 
                
                
                
               }
        } );
        
        
        primaryStage.show();
       
        
    }
       
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    class MyEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent t) {
         //   System.out.println("Clicked");
        }
    }
    
    /* Main Board Logic in function firstScene */ 
    
    private Scene firstScene(Stage stage,String s1, String s2, String s3,Image imageX)
    {
        BorderPane bp = new BorderPane();
        Image img1 = imageX;
        Image img2 = new Image(getClass().getResourceAsStream("images/Blank.png"));
        Image[] img = new Image[54];
        String string;
        int var;
        int size = gridSize(s3);
       if(s1.equals(""))
        s1 = "#Player1";
        if(s2.equals(""))
            s2 = "#Player2";
        int row=0,col=0;
        GridPane gp = new GridPane(); /*GridPane */
        String image = JavaFXApplication1.class.getResource("images/Image7.jpg").toExternalForm();
        bp.setStyle("-fx-background-image: url('" + image + "');-fx-background-position: center center;-fx-background-repeat: stretch;");
        gp.setAlignment(Pos.CENTER);
        ImageView iv;
        final ImageView iv2 = new ImageView();
        
        iv2.setImage(img1);
       // ImageView iv3 = new ImageView();
        
        gp.setHgap(4);
        gp.setVgap(4);
        
        Random rand = new Random();
        int total_size = size * size;
        int[] num = new int[total_size/2];
        int[] numb = new int[size*size];
        
        int ran;
        int i=0;
        while(true)
        {
            ran = rand.nextInt(1000);
            if(i<total_size)
            {
                if(num[ran%(total_size/2)]==2)
                    continue;    
                else
                {
                numb[i] = ran%(total_size/2);
                num[ran%(total_size/2)]++;  
                i++;
                }
            }
            else
                break;          
            
        }
        
        for(i=0;i<54;i++)
        {
        var = i+1;
        string = String.format("images/%d.png", var);
        img[i] = new Image(getClass().getResourceAsStream(string));
        }
        
        Image temp;
        for (int d=0;d<54;d++) {
                    int index=(int)(Math.random()* 54);
		    temp=img[d];
		    img[d]=img[index];
		    img[index]=temp;
		  }
        
        
        Text title = new Text("The Mind Game");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        
         HBox hb = new HBox();
         hb.getChildren().add(title);
         hb.setPrefHeight(50);
         hb.setAlignment(Pos.CENTER);
         //VBox
         VBox vbox1 = new VBox();
         vbox1.setAlignment(Pos.CENTER);
         vbox1.setPadding(new Insets(10));
         vbox1.setPrefWidth(200);
         vbox1.setSpacing(8);
        
        title = new Text(s1);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Label label1 = new Label();
        label1.setText("0");
        label1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        vbox1.getChildren().add(title);
         vbox1.getChildren().add(label1);
        VBox vbox2 = new VBox();
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setPrefWidth(200);
         vbox2.setPadding(new Insets(10));
         vbox2.setSpacing(8);
        
        title = new Text(s2);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Label label2 = new Label();
        label2.setText("0");
        label2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        vbox2.getChildren().add(title);
        vbox2.getChildren().add(label2);
         
         
         bp.setTop(hb);
         bp.setLeft(vbox1);
         bp.setRight(vbox2);
         bp.setCenter(gp);
         for(i=0;i<total_size;i++)
         {   
          iv = new ImageView();
          iv.setImage(img1);
          if(size==6)
          {
          iv.setFitWidth(72);
          iv.setFitHeight(96);
          }
          else if(size==8)
          {
          iv.setFitWidth(54);
          iv.setFitHeight(72);
          }
          else
          {
          iv.setFitWidth(45);
          iv.setFitHeight(60);
          }
         if(i%size==0)
         {
             col = 0;
             row++;
         }
         col++;
         gp.add(iv, col, row);
         
         }
         
         gp.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler(){
              int previous=0,now=0;
              int player1=0,player2=0,turn=0,con=0;
              int prevX=0,prevY=0,nowX=0,nowY=0;
              int count = 0;
             @Override
             public void handle(MouseEvent t){
                 
                 
                Node target = (Node)t.getTarget();
               // gp.getChildren().remove(target);
                
                int x,y;
               
                x = gp.getRowIndex(target) - 1 ;
                y = gp.getColumnIndex(target) - 1 ;
                
            //    System.out.println(x);
             //   System.out.println(y);
                if(count == 0)
                { 
                 
                     previous = 6*x + y;
                    
                     prevX = x;
                                         
                     prevY = y;
                                          
                     count++;
                                         
                     ImageView ivx = new ImageView();
                     ivx.setImage(img[numb[6*prevX+prevY]]);
                     if(size==6)
                       {
                        ivx.setFitWidth(72);
                        ivx.setFitHeight(96);
                        }
                        else if(size==8)
                        {
                        ivx.setFitWidth(54);
                        ivx.setFitHeight(72);
                        }
                        else
                        {
                        ivx.setFitWidth(45);
                        ivx.setFitHeight(60);
                       }
                     gp.add(ivx, (prevY+1),(prevX+1));
                } 
                else if(count==1)
                {
                     now = 6*x + y;
                     nowX = x;
                     nowY = y;
                     count=0;
                     ImageView ivx = new ImageView();
                     ivx.setImage(img[numb[6*nowX+nowY]]);
                     if(size==6)
                            {
                            ivx.setFitWidth(72);
                            ivx.setFitHeight(96);
                            }
                            else if(size==8)
                            {
                            ivx.setFitWidth(54);
                            ivx.setFitHeight(72);
                            }
                            else
                            {
                            ivx.setFitWidth(45);
                            ivx.setFitHeight(60);
                            }
                     
                     
                     
                     gp.add(ivx, (nowY+1),(nowX+1) );
                     
                     
                     
                     HBox layout = new HBox(10);
                 
                     layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
                      Stage prim = new Stage();
                     // prim.initModality(Modality.APPLICATION_MODAL); 
                     // prim.initOwner(prim.getScene().getWindow());
                      
                      Label labelX = new Label("Hurraayyy");
                      Label labelY = new Label("Oops");
                      if(numb[previous]==numb[now])
                      layout.getChildren().add(labelX);
                      else
                      layout.getChildren().add(labelY);
                      Button show = new Button("Proceed");
                      layout.getChildren().addAll(show);
                      prim.setScene(new Scene(layout, 120, 50));
                      prim.initModality(Modality.APPLICATION_MODAL);
                      prim.show();
                      
                      show.setOnAction(new EventHandler<ActionEvent>() {

                         @Override
                         public void handle(ActionEvent event) {
                             prim.close();
                             
                            
                             if(numb[previous]==numb[now])
                             {
                             if(turn==0)
                             {
                             player1++;
                             label1.setText(Integer.toString(5*player1));
                             turn++;
                             con++;
                             }
                             else
                             {
                             player2++;
                             label2.setText(Integer.toString(5*player2));
                             turn--;
                             con++;
                             }
                             HBox hb1 = new HBox();
                             hb1.getChildren().add(labelX);
                             ImageView ivx = new ImageView();
                             ivx.setImage(img2);
                             gp.add(ivx, (prevY+1),(prevX+1) );
                             ivx = new ImageView();
                             ivx.setImage(img2);
                             if(size==6)
                                {
                                ivx.setFitWidth(72);
                                ivx.setFitHeight(96);
                                }
                                else if(size==8)
                                {
                                ivx.setFitWidth(54);
                                ivx.setFitHeight(72);
                                }
                                else
                                {
                                ivx.setFitWidth(45);
                                ivx.setFitHeight(60);
                                }
                             gp.add(ivx, (nowY+1),(nowX+1) );
                             }
                             else
                             {
                                 
                             HBox hb1 = new HBox();
                             if(turn==0)
                             turn++;
                             else
                                 turn--;
                             hb1.getChildren().add(labelY);
                             ImageView ivx = new ImageView();
                             ivx.setImage(img1);
                             if(size==6)
                                {
                                ivx.setFitWidth(72);
                                ivx.setFitHeight(96);
                                }
                                else if(size==8)
                                {
                                ivx.setFitWidth(54);
                                ivx.setFitHeight(72);
                                }
                                else
                                {
                                ivx.setFitWidth(45);
                                ivx.setFitHeight(60);
                                }
                             gp.add(ivx, (prevY+1),(prevX+1) );
                             ivx = new ImageView();
                             ivx.setImage(img1);
                             if(size==6)
                                {
                                ivx.setFitWidth(72);
                                ivx.setFitHeight(96);
                                }
                                else if(size==8)
                                {
                                ivx.setFitWidth(54);
                                ivx.setFitHeight(72);
                                }
                                else
                                {
                                ivx.setFitWidth(45);
                                ivx.setFitHeight(60);
                                }
                             gp.add(ivx, (nowY+1),(nowX+1) );
                             }
                             
                              if(con==total_size/2)
                             {
                             AnchorPane ap = new AnchorPane();
                             ap.setPrefSize(200, 200);
                             Label labelP = new Label();
                             if(player1>player2)
                             labelP.setText("Player1 Wins...!!!");
                             else if(player1==player2)
                             labelP.setText("Its a tie...!!!");
                             else
                             labelP.setText("Player2 Wins...!!!");
                             
                             ap.getChildren().add(labelP);
                             Button btw = new Button("Exit");
                             ap.setTopAnchor(btw, 30d);
                             ap.setTopAnchor(labelP, 0d);
                             ap.getChildren().add(btw);
                             Scene sc3 = new Scene(ap);
                             Stage player = new Stage();
                             player.setScene(sc3);
                             player.initModality(Modality.APPLICATION_MODAL);
                             player.show();
                             btw.setOnAction(new EventHandler<ActionEvent>() {

                                 @Override
                                 public void handle(ActionEvent event) {
                                     player.close();
                                     stage.close();
                                 }
                             });
                                                           
                             }
                             
                             
                             
                         }
                     });
                     
                }
                   
             } 
         });  
         
        
        Scene sc = new Scene(bp);
        return sc;
        
        
    }
    
    /* In order to get Grid Size */
    public int gridSize(String s){
        int x;
        switch(s){
            case "8X8" :
                x = 8;
                break;
            case "10X10" :
                x = 10;
                break;
            default :  
                x = 6;
     
        }
        
        return x;
        
    }
   
   
   
   
   
   
   
   
    
   
}
