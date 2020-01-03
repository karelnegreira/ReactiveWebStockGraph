package com.example.preciosstocks2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

import static com.example.preciosstocks2.ChartApplication.*;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

@Value("classpath:/chart.fxml")
    private Resource chartResource;
    private String applicationTitle;
    private ApplicationContext applicationContext;
    StockCodeContainer stockCodeContainer;
    TextField stockText = new TextField();

    public StageInitializer(@Value("${spring.application.ui.title}")String applicationTitle, ApplicationContext applicationContext){
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            stockCodeContainer = new StockCodeContainer(stockText.getText());
           // System.out.println(stockCodeContainer.getSymbol());
            final FXMLLoader[] fxmlLoader = {new FXMLLoader(chartResource.getURL())};
            final Stage[] stage = {event.getStage()};
            stockText = new TextField("GOOG");
            stockCodeContainer = new StockCodeContainer(stockText.getText());
            final Button[] button = {new Button("Submit Live Stock")};

            fxmlLoader[0].setControllerFactory(aClass -> applicationContext.getBean(aClass));
            final Parent[] parent = {fxmlLoader[0].load()};
            final StackPane[] stackPaneLayout = {new StackPane()};
            stackPaneLayout[0].setAlignment(Pos.BOTTOM_CENTER);
            stackPaneLayout[0].getChildren().add(stockText);
            stackPaneLayout[0].getChildren().add(button[0]);

            VBox vBox = new VBox();
            vBox.getChildren().add(parent[0]);
            vBox.getChildren().add(stackPaneLayout[0]);

            Scene scene = new Scene(vBox,800,600);


            stage[0].setScene(scene);
            //  stage.setScene(new Scene(stackPaneLayout,300, 600));
            stage[0].setTitle(applicationTitle);
            stage[0].show();

            button[0].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                  //  ChartApplication chartApplication = new ChartApplication();
                   // chartApplication.stop();
                    try {

                        stockCodeContainer = new StockCodeContainer(stockText.getText());
                        System.out.println(stockCodeContainer.getSymbol());


                      //  stockCodeContainer = new StockCodeContainer(stockText.getText());
                        // System.out.println(stockCodeContainer.getSymbol());
                         fxmlLoader[0] =  new FXMLLoader(chartResource.getURL());
                         stage[0] = event.getStage();
                       // stockText = new TextField("GOOG");
                      //  stockCodeContainer = new StockCodeContainer(stockText.getText());
                         button[0] = new Button("Submit Live Stock");

                      //  fxmlLoader[0].setControllerFactory(aClass -> applicationContext.getBean(aClass));
                         parent[0] = fxmlLoader[0].load();
                         stackPaneLayout[0] = new StackPane();
                        stackPaneLayout[0].setAlignment(Pos.BOTTOM_CENTER);
                        stackPaneLayout[0].getChildren().add(stockText);
                        stackPaneLayout[0].getChildren().add(button[0]);

                        VBox vBox = new VBox();
                        vBox.getChildren().add(parent[0]);
                        vBox.getChildren().add(stackPaneLayout[0]);

                        Scene scene = new Scene(vBox,800,600);


                        stage[0].setScene(scene);
                        //  stage.setScene(new Scene(stackPaneLayout,300, 600));
                        stage[0].setTitle(applicationTitle);
                        stage[0].show();



                    }catch(Exception e){
                     System.out.println(e.getMessage());
                    }
                   // chartApplication.start(stage);
                  //  StageInitializer  stageInitializer = new StageInitializer(applicationTitle, applicationContext);
                   // stageInitializer.onApplicationEvent(event);

                }
            });

        } catch (IOException e) {
            throw new RuntimeException();
        }catch (Exception e){
           throw new RuntimeException();
        }
    }
}
