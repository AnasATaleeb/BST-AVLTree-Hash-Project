package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {
	Stage primaryStage1 = new Stage();
	AVL<Department> department = new AVL<Department>();
	Alert error = new Alert(AlertType.ERROR);
	Alert success = new Alert(AlertType.INFORMATION);

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane pane = new BorderPane();
			Label comName = new Label("Company Name: ");
			TextField coField = new TextField();
			Label price = new Label("Price : ");
			TextField priceField = new TextField();
			Label numOfSeat = new Label("Number Of Seats: ");
			TextField numseatField = new TextField();
			numseatField.setDisable(true);
			Label load = new Label("Load : ");
			TextField loadField = new TextField();
			loadField.setDisable(true);
			Label numOfPass = new Label("Number Of Passengers : ");
			TextField numpassField = new TextField();
			numpassField.setDisable(true);
			
			GridPane grid = new GridPane();
			
			grid.add(comName, 0, 0);
			grid.add(coField,1,0);
			grid.add(price, 0, 1);
			grid.add(priceField, 1, 1);
			grid.add(numOfSeat, 0, 2);
			grid.add(numseatField, 1, 2);
			grid.add(load, 0, 3);
			grid.add(loadField, 1, 3);
			grid.add(numOfPass, 0, 4);
			grid.add(numpassField, 1, 4);
			
			RadioButton car = new RadioButton("Car");
			RadioButton truck = new RadioButton("Truck");
			RadioButton bus = new RadioButton("Bus");
			ToggleGroup tg = new ToggleGroup();
			tg.getToggles().addAll(car,truck,bus);
			
			HBox h = new HBox(5,car,truck,bus);
			h.setAlignment(Pos.CENTER);
			
			grid.add(h, 1, 5);
			
			Button add = new Button("Add");
			Button show = new Button("Show Sorted");
			
			HBox h1 = new HBox(20,add,show);
			h1.setAlignment(Pos.CENTER);
			
			grid.add(h1, 1, 6);
			
			grid.setHgap(5);
			grid.setVgap(5);
			
			grid.setPadding(new Insets(10));
			grid.setAlignment(Pos.CENTER);
			
			TextArea area = new TextArea();
			area.setMaxHeight(200);
			area.setMaxWidth(400);
			
			HBox h3 = new HBox(60,grid,area);
			h3.setAlignment(Pos.CENTER);
			h3.setPadding(new Insets(20));
			
			
			car.setOnAction(e->{
				numseatField.setDisable(false);
				numpassField.setDisable(true);
				loadField.setDisable(true);
			});
			
			truck.setOnAction(e ->{
				loadField.setDisable(false);
				numseatField.setDisable(true);
				numpassField.setDisable(true);
			});
			
			bus.setOnAction(e ->{
				numpassField.setDisable(false);
				loadField.setDisable(true);
				numseatField.setDisable(true);
			});
			
			pane.setCenter(h3);
			Scene scene = new Scene(pane, 800, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			error.setContentText("Error! File Empty");
			error.show();
		}
	}

	private void studentAction(Stage primaryStage) {
		BorderPane pane = new BorderPane();

		Button printtable = new Button("Print Hash Table");
		printtable.setPrefSize(300, 80);
		printtable.setOnAction(e -> {
			printtableAct(primaryStage);
		});
		icons(printtable);
		butoonEffect(printtable);
		Button printsize = new Button("Print Table Size");
		printsize.setPrefSize(300, 80);
		printsize.setOnAction(e -> {
			printsizeAct(primaryStage);
		});
		icons(printsize);
		butoonEffect(printsize);

		Button printout = new Button("Print out used hash function");
		printout.setPrefSize(300, 80);
		printout.setOnAction(e -> {
			printoutAct(primaryStage);
		});
		icons(printout);
		butoonEffect(printout);

		Button insert = new Button("Insert a new record to hash table");
		insert.setPrefSize(300, 80);
		insert.setOnAction(e -> {
			insertAct(primaryStage);
		});
		icons(insert);
		butoonEffect(insert);

		Button search = new Button("Search for a specific record");
		search.setPrefSize(300, 80);
		search.setOnAction(e -> {
			searchAct(primaryStage);
		});
		icons(search);
		butoonEffect(search);

		Button delete = new Button("Delete a specific record");
		delete.setPrefSize(300, 80);
		delete.setOnAction(e -> {
			deleteAct(primaryStage);
		});
		icons(delete);
		butoonEffect(delete);

		Button save = new Button("Save hash table back to file");
		save.setPrefSize(300, 80);
		save.setOnAction(e -> {
			saveAct(primaryStage);
		});
		icons(save);
		butoonEffect(save);

		Button back = new Button("back main page");
		back.setPrefSize(300, 80);
		back.setOnAction(e -> {
			start(primaryStage);
		});
		icons(back);
		butoonEffect(back);

		VBox v = new VBox(15, printtable, printsize, printout, insert, search, delete, save, back);
		v.setAlignment(Pos.CENTER);

		pane.setCenter(v);
		pane.setStyle("-fx-background-color: linear-gradient(to right, #acb6e5, #86fde8);");

		Scene scene = new Scene(pane, 1535, 800);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void printsizeAct(Stage primaryStage) {
		BorderPane pane1 = new BorderPane();

		Label depname = new Label("Department name : ");
		depname.setPadding(new Insets(15));
		TextField depnamet = new TextField();
		depnamet.setPrefSize(200, 50);
		IconedTextFieled(depname, depnamet);
		HBox h2 = new HBox(depname, depnamet);
		h2.setAlignment(Pos.CENTER);

		Label studenttt = new Label("Size : ");
		studenttt.setPadding(new Insets(15));
		TextField studentttt = new TextField();
		studentttt.setPrefSize(200, 50);
		IconedTextFieled(studenttt, studentttt);
		HBox h1 = new HBox(studenttt, studentttt);
		h1.setAlignment(Pos.CENTER);

		Button cal = new Button("print");
		cal.setOnAction(e -> {
			Department tdep = department.find(new Department(depnamet.getText(), null));
			if (tdep != null) {
				studentttt.setText(tdep.getHashTable().getTableSize() + "");
			}

		});
		icons(cal);
		butoonEffect(cal);

		VBox v = new VBox(20, h2, h1, cal);
		v.setAlignment(Pos.CENTER);

		pane1.setCenter(v);
		pane1.setStyle("-fx-background-color: linear-gradient(to right, #acb6e5, #86fde8);");

		Scene scene = new Scene(pane1, 400, 400);
		primaryStage1.setScene(scene);
		primaryStage1.show();

	}

	private void printoutAct(Stage primaryStage) {
		BorderPane pane1 = new BorderPane();

		Label studenttt = new Label("Hash Function : \n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		studenttt.setPadding(new Insets(10));
		TextArea studentttt = new TextArea();
		studentttt.setPrefSize(400, 300);
		IconedTextFieled(studenttt, new Label());
		HBox h1 = new HBox(studenttt, studentttt);
		h1.setAlignment(Pos.CENTER);

		Button cal = new Button("print");
		cal.setOnAction(e -> {
			studentttt.setText("@Override\n" + "public int hashCode() {\n" + "int hash = 0;\n"
					+ "for (int i = 0; i < name.length(); i++) {\n" + "hash = hash + name.charAt(i);\n" + "}\n"
					+ "return hash;\n" + "}");
		});
		icons(cal);
		butoonEffect(cal);

		VBox v = new VBox(20, h1, cal);
		v.setAlignment(Pos.CENTER);

		pane1.setCenter(v);
		pane1.setStyle("-fx-background-color: linear-gradient(to right, #acb6e5, #86fde8);");

		Scene scene = new Scene(pane1, 600, 600);
		primaryStage1.setScene(scene);
		primaryStage1.show();
	}

	private void insertAct(Stage primaryStage) {
		BorderPane pane1 = new BorderPane();

		Label depname = new Label("Department name : ");
		depname.setPadding(new Insets(7));
		TextField depnamet = new TextField();
		depnamet.setPrefSize(150, 35);
		IconedTextFieled(depname, depnamet);
		HBox h2 = new HBox(depname, depnamet);
		h2.setAlignment(Pos.CENTER);

		Label studentgender = new Label("student gender : ");
		studentgender.setPadding(new Insets(7));
		TextField studentgendert = new TextField();
		studentgendert.setPrefSize(150, 35);
		IconedTextFieled(studentgender, studentgendert);
		HBox h = new HBox(studentgender, studentgendert);
		h.setAlignment(Pos.CENTER);
		Label studentid = new Label("student id : ");
		studentid.setPadding(new Insets(7));
		TextField studentidt = new TextField();
		studentidt.setPrefSize(150, 35);
		IconedTextFieled(studentid, studentidt);
		HBox h1 = new HBox(studentid, studentidt);
		h1.setAlignment(Pos.CENTER);

		Label studentname = new Label("student name : ");
		studentname.setPadding(new Insets(7));
		TextField studentnamet = new TextField();
		studentnamet.setPrefSize(150, 35);
		IconedTextFieled(studentname, studentnamet);
		HBox h3 = new HBox(studentname, studentnamet);
		h3.setAlignment(Pos.CENTER);

		Label studentavg = new Label("student avg : ");
		studentavg.setPadding(new Insets(7));
		TextField studentavgt = new TextField();
		studentavgt.setPrefSize(150, 35);
		IconedTextFieled(studentavg, studentavgt);
		HBox h4 = new HBox(studentavg, studentavgt);
		h4.setAlignment(Pos.CENTER);

		Button cal = new Button("add");
		cal.setOnAction(e -> {
			Department tdep = department.find(new Department(depnamet.getText(), null));
			if (tdep != null) {
				tdep.getHashTable().insert(new Student(studentnamet.getText(), Integer.parseInt(studentidt.getText()),
						Double.parseDouble(studentavgt.getText()), studentgendert.getText()));
			}

		});
		icons(cal);
		butoonEffect(cal);

		VBox v = new VBox(20, h2, h, h1, h3, h4, cal);
		v.setAlignment(Pos.CENTER);

		pane1.setCenter(v);
		pane1.setStyle("-fx-background-color: linear-gradient(to right, #acb6e5, #86fde8);");

		Scene scene = new Scene(pane1, 400, 400);
		primaryStage1.setScene(scene);
		primaryStage1.show();

	}

	private void searchAct(Stage primaryStage) {
		BorderPane pane1 = new BorderPane();

		Label depname = new Label("Department name : ");
		depname.setPadding(new Insets(7));
		TextField depnamet = new TextField();
		depnamet.setPrefSize(150, 35);
		IconedTextFieled(depname, depnamet);
		HBox h2 = new HBox(depname, depnamet);
		h2.setAlignment(Pos.CENTER);

		Label student = new Label("student name : ");
		TextField studentt = new TextField();
		student.setPadding(new Insets(7));
		studentt.setPrefSize(150, 35);
		IconedTextFieled(student, studentt);

		HBox h = new HBox(student, studentt);
		h.setAlignment(Pos.CENTER);
		Label studenttt = new Label("student : ");
		studenttt.setPadding(new Insets(7));
		TextField studentttt = new TextField();
		studentttt.setPrefWidth(250);
		IconedTextFieled(studenttt, studentttt);
		HBox h1 = new HBox(studenttt, studentttt);
		h1.setAlignment(Pos.CENTER);

		Button cal = new Button("search");
		cal.setOnAction(e -> {
			Department tdep = department.find(new Department(depnamet.getText(), null));
			if (tdep != null) {
				studentttt.setText(tdep.getHashTable().search(new Student(studentt.getText(), 0, 0, null)) + "");
			}

		});
		icons(cal);
		butoonEffect(cal);

		VBox v = new VBox(20, h2, h, h1, cal);
		v.setAlignment(Pos.CENTER);

		pane1.setCenter(v);
		pane1.setStyle("-fx-background-color: linear-gradient(to right, #acb6e5, #86fde8);");

		Scene scene = new Scene(pane1, 400, 400);
		primaryStage1.setScene(scene);
		primaryStage1.show();
	}

	private void deleteAct(Stage primaryStage) {
		BorderPane pane1 = new BorderPane();

		Label depname = new Label("Department name : ");
		depname.setPadding(new Insets(7));
		TextField depnamet = new TextField();
		IconedTextFieled(depname, depnamet);

		HBox h2 = new HBox(depname, depnamet);
		h2.setAlignment(Pos.CENTER);

		Label student = new Label("student name : ");
		student.setPadding(new Insets(7));
		TextField studentt = new TextField();
		IconedTextFieled(student, studentt);
		HBox h = new HBox(student, studentt);
		h.setAlignment(Pos.CENTER);

		Button cal = new Button("remove");
		cal.setOnAction(e -> {
			Department tdep = department.find(new Department(depnamet.getText(), null));
			if (tdep != null) {
				tdep.getHashTable().delete(new Student(studentt.getText(), 0, 0, null));
			}
		});
		icons(cal);
		butoonEffect(cal);

		VBox v = new VBox(20, h2, h, cal);
		v.setAlignment(Pos.CENTER);

		pane1.setCenter(v);
		pane1.setStyle("-fx-background-color: linear-gradient(to right, #acb6e5, #86fde8);");

		Scene scene = new Scene(pane1, 400, 400);
		primaryStage1.setScene(scene);
		primaryStage1.show();

	}

	private void saveAct(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		
		Label depname = new Label("Department name : ");
		depname.setPadding(new Insets(7));
		TextField depnamet = new TextField();
		depnamet.setPrefSize(150, 35);
		IconedTextFieled(depname, depnamet);
		HBox h2 = new HBox(depname, depnamet);
		h2.setAlignment(Pos.CENTER);
		
		
		Button cal = new Button("save in file ");
		cal.setOnAction(e -> {
			try {
				Department tdep = department.find(new Department(depnamet.getText(), null));
	            File writer = new File(""+tdep.getDepartmentRelatedDataFileName());
	            PrintWriter writer1 = new PrintWriter(writer);
	            for(int i = 0; i < tdep.getHashTable().getTableSize(); i++) {
	            	if( tdep.getHashTable().getNodes()[i].getF() == 'F') {
	                	writer1.println(tdep.getHashTable().getNodes()[i].getData().getName() + "/" + tdep.getHashTable().getNodes()[i].getData().getId() + "/" + tdep.getHashTable().getNodes()[i].getData().getAvg() + "/" + tdep.getHashTable().getNodes()[i].getData().getGender());
	                }
	            }
	            writer1.close();
	        } catch (Exception ee) {
	            System.out.println("Error in saving students");
	        }

		});
		icons(cal);
		butoonEffect(cal);
		
		
		VBox v = new VBox(20 , h2 ,cal);
		v.setAlignment(Pos.CENTER);
		
		pane.setCenter(v);
		pane.setStyle("-fx-background-color: linear-gradient(to right, #acb6e5, #86fde8);");

		Scene scene = new Scene(pane, 400, 400);
		primaryStage1.setScene(scene);
		primaryStage1.show();
	}

	private void printtableAct(Stage primaryStage) {
		BorderPane pane1 = new BorderPane();

		Label depname = new Label("Department name : ");
		depname.setPadding(new Insets(7));
		TextField depnamet = new TextField();
		IconedTextFieled(depname, depnamet);
		HBox h2 = new HBox(depname, depnamet);
		h2.setAlignment(Pos.CENTER);

		Label studenttt = new Label("student : \n\n\n\n\n\n\n\n\n\n\n\n");
		studenttt.setPadding(new Insets(60));
		TextArea studentttt = new TextArea();
		studentttt.setPrefSize(500, 300);
		IconedTextFieled(studenttt, new Label());
		HBox h1 = new HBox(studenttt, studentttt);
		h1.setAlignment(Pos.CENTER);

		Button cal = new Button("print");
		cal.setOnAction(e -> {
			Department tdep = department.find(new Department(depnamet.getText(), null));
			if (tdep != null) {
				studentttt.setText(tdep.getHashTable().toString());
			}

		});
		icons(cal);
		butoonEffect(cal);

		VBox v = new VBox(20, h2, h1, cal);
		v.setAlignment(Pos.CENTER);

		pane1.setCenter(v);
		pane1.setStyle("-fx-background-color: linear-gradient(to right, #acb6e5, #86fde8);");

		Scene scene = new Scene(pane1, 1000, 600);
		primaryStage1.setScene(scene);
		primaryStage1.show();

	}

	private void depAction(Stage primaryStage) {
		BorderPane pane = new BorderPane();

		Button add = new Button("add");
		add.setPrefSize(200, 100);
		add.setOnAction(e -> {
			adddepAct(primaryStage);
		});
		icons(add);
		butoonEffect(add);

		Button search = new Button("search");
		search.setPrefSize(200, 100);
		search.setOnAction(e -> {
			searchdepAct(primaryStage);
		});
		icons(search);
		butoonEffect(search);

		Button remove = new Button("delete");
		remove.setPrefSize(200, 100);
		remove.setOnAction(e -> {
			removedepAct(primaryStage);
		});
		icons(remove);
		butoonEffect(remove);

		Button trave = new Button("traverse in order ");
		trave.setPrefSize(200, 100);
		trave.setOnAction(e -> {
			travedepAct(primaryStage);
		});
		icons(trave);
		butoonEffect(trave);

		Button calc = new Button("calculate hight");
		calc.setPrefSize(200, 100);
		calc.setOnAction(e -> {
			calcdepAct(primaryStage);
		});
		icons(calc);
		butoonEffect(calc);

		Button back = new Button("back main page");
		back.setPrefSize(200, 100);
		back.setOnAction(e -> {
			start(primaryStage);
		});
		icons(back);
		butoonEffect(back);

		VBox v = new VBox(15, add, search, remove, trave, calc, back);
		v.setAlignment(Pos.CENTER);

		pane.setCenter(v);
		pane.setStyle("-fx-background-color: linear-gradient(to right, #acb6e5, #86fde8);");

		Scene scene = new Scene(pane, 1535, 800);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void calcdepAct(Stage primaryStage) {
		BorderPane pane1 = new BorderPane();

		Label deppath = new Label("Hight : ");
		deppath.setPadding(new Insets(7));
		TextField deppatht = new TextField();
		IconedTextFieled(deppath, deppatht);
		HBox h1 = new HBox(deppath, deppatht);
		h1.setAlignment(Pos.CENTER);

		Button cal = new Button("calculate");
		cal.setOnAction(e -> {
			deppatht.setText(department.height() + "");
		});
		icons(cal);
		butoonEffect(cal);

		VBox v = new VBox(20, h1, cal);
		v.setAlignment(Pos.CENTER);

		pane1.setCenter(v);
		pane1.setStyle("-fx-background-color: linear-gradient(to right, #acb6e5, #86fde8);");

		Scene scene = new Scene(pane1, 400, 400);
		primaryStage1.setScene(scene);
		primaryStage1.show();

	}

	private void travedepAct(Stage primaryStage) {
		BorderPane pane1 = new BorderPane();

		Label deppath = new Label("Department: \n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		deppath.setPadding(new Insets(7));
		IconedTextFieled(deppath, new Label());
		TextArea deppatht = new TextArea();
		deppatht.setPrefSize(300, 200);
		HBox h1 = new HBox(deppath, deppatht);
		h1.setAlignment(Pos.CENTER);

		Button cal = new Button("traverse");
		cal.setOnAction(e -> {
			deppatht.setText(department.toStringInorder());
		});
		icons(cal);
		butoonEffect(cal);

		VBox v = new VBox(20, h1, cal);
		v.setAlignment(Pos.CENTER);

		pane1.setCenter(v);
		pane1.setStyle("-fx-background-color: linear-gradient(to right, #acb6e5, #86fde8);");

		Scene scene = new Scene(pane1, 600,500);
		primaryStage1.setScene(scene);
		primaryStage1.show();

	}

	private void removedepAct(Stage primaryStage) {
		BorderPane pane1 = new BorderPane();

		Label depname = new Label("Department name : ");
		depname.setPadding(new Insets(7));
		TextField depnamet = new TextField();
		IconedTextFieled(depname, depnamet);
		HBox h = new HBox(depname, depnamet);
		h.setAlignment(Pos.CENTER);

		Button cal = new Button("remove");
		cal.setOnAction(e -> {
			Department d = department.find(new Department(depnamet.getText(), null));
			department.delete(d);
		});
		icons(cal);
		butoonEffect(cal);

		VBox v = new VBox(20, h, cal);
		v.setAlignment(Pos.CENTER);

		pane1.setCenter(v);
		pane1.setStyle("-fx-background-color: linear-gradient(to right, #acb6e5, #86fde8);");

		Scene scene = new Scene(pane1, 400, 400);
		primaryStage1.setScene(scene);
		primaryStage1.show();

	}

	private void searchdepAct(Stage primaryStage) {
		BorderPane pane1 = new BorderPane();

		Label depname = new Label("Department name : ");
		depname.setPadding(new Insets(7));
		TextField depnamet = new TextField();
		IconedTextFieled(depname, depnamet);

		HBox h = new HBox(depname, depnamet);
		h.setAlignment(Pos.CENTER);
		Label deppath = new Label("Department  : ");
		deppath.setPadding(new Insets(7));
		TextField deppatht = new TextField();
		deppatht.setEditable(false);
		IconedTextFieled(deppath, deppatht);
		HBox h1 = new HBox(deppath, deppatht);
		h1.setAlignment(Pos.CENTER);

		Button cal = new Button("search");
		cal.setOnAction(e -> {
			Department tdep = department.find(new Department(depnamet.getText(), null));
			if (tdep != null) {
				deppatht.setText(tdep.toString());
			}
		});
		icons(cal);
		butoonEffect(cal);

		VBox v = new VBox(20, h, h1, cal);
		v.setAlignment(Pos.CENTER);

		pane1.setCenter(v);
		pane1.setStyle("-fx-background-color: linear-gradient(to right, #acb6e5, #86fde8);");

		Scene scene = new Scene(pane1, 400, 400);
		primaryStage1.setScene(scene);
		primaryStage1.show();
	}

	private void adddepAct(Stage primaryStage) {
		BorderPane pane1 = new BorderPane();

		Label depname = new Label("Department name : ");
		depname.setPadding(new Insets(7));
		TextField depnamet = new TextField();
		IconedTextFieled(depname, depnamet);

		HBox h = new HBox(depname, depnamet);
		h.setAlignment(Pos.CENTER);
		Label deppath = new Label("Department path : ");
		deppath.setPadding(new Insets(7));
		TextField deppatht = new TextField();
		IconedTextFieled(deppath, deppatht);
		HBox h1 = new HBox(deppath, deppatht);
		h1.setAlignment(Pos.CENTER);

		Button cal = new Button("add");
		cal.setOnAction(e -> {
			department.insert(new Department(depnamet.getText(), deppatht.getText() + ".txt"));
		});
		icons(cal);
		butoonEffect(cal);

		VBox v = new VBox(20, h, h1, cal);
		v.setAlignment(Pos.CENTER);

		pane1.setCenter(v);
		pane1.setStyle("-fx-background-color: linear-gradient(to right, #acb6e5, #86fde8);");

		Scene scene = new Scene(pane1, 400, 400);
		primaryStage1.setScene(scene);
		primaryStage1.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void IconedTextFieled(Node l, Node t) {
		l.setStyle("-fx-border-color: #d8d9e0;" + "-fx-font-size: 14;\n" + "-fx-border-width: 1;"
				+ "-fx-border-radius: 50;" + "-fx-font-weight: Bold;\n" + "-fx-background-color:#d8d9e0;"
				+ "-fx-background-radius: 50 0 0 50");

		t.setStyle("-fx-border-radius: 0 50 50 0;\n" + "-fx-font-size: 14;\n" + "-fx-font-family: Times New Roman;\n"
				+ "-fx-font-weight: Bold;\n" + "-fx-background-color: #f6f6f6;\n" + "-fx-border-color: #d8d9e0;\n"
				+ "-fx-border-width:  3.5;" + "-fx-background-radius: 0 50 50 0");
	}

	private void butoonEffect(Node b) {
		b.setOnMouseMoved(e -> {
			b.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 15;\n"
					+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n" + " -fx-text-fill: #CE2029;\n"
					+ "-fx-background-color: #d8d9e0;\n" + "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  3.5;"
					+ "-fx-background-radius: 25 25 25 25");
		});

		b.setOnMouseExited(e -> {
			b.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 15;\n"
					+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n" + " -fx-text-fill: #f2f3f4;\n"
					+ "-fx-background-color: transparent;\n" + "-fx-border-color: #d8d9e0;\n"
					+ "-fx-border-width:  3.5;" + "-fx-background-radius: 25 25 25 25");
		});
	}

	private void icons(Node l) {
		l.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 15;\n" + "-fx-font-family: Times New Roman;\n"
				+ "-fx-font-weight: Bold;\n" + " -fx-text-fill: #f2f3f4;\n" + "-fx-background-color: transparent;\n"
				+ "-fx-border-color: #d8d9e0;\n" + "-fx-border-width:  3.5;" + "-fx-background-radius: 25 25 25 25");
	}

	private boolean readDepartments() throws FileNotFoundException {
		try {
			File fr = new File("departments.txt");
			Scanner fileScanner = new Scanner(fr);
			String[] token;
			if (fr.length() > 0) {
				try {
					while (fileScanner.hasNextLine()) {
						token = fileScanner.nextLine().split("/");
						if (department.find(new Department(token[0].trim(), token[1].trim())) == null) {
							Department dep = new Department(token[0].trim(), token[1].trim());
							department.insert(dep);
							File file = new File(token[1].trim());
							if (!file.exists()) {
								file.createNewFile();
							} else {
								System.out.println("File already exists " + token[1]);
								readStudentDep(dep);
							}
						} else
							System.out.println("duplicated name for :" + token[0]);
					}
					System.out.println("All department data was loaded successfully");
					fileScanner.close();
					return true; 
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Warning: department data file is empty");
				}
			} else {
				System.out.println("Warning: department data file is empty");
			}
		} catch (Exception error) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setTitle("Error");
			a.setContentText("Department couldn't be found !!");
			a.setHeaderText("Final project");
			a.show();
			return false;
		}
		return false;
	}

	public boolean readStudentDep(Department department) {
		if (department != null) {
			try {
				File file = new File(department.getDepartmentRelatedDataFileName().trim());
				Scanner fileScanner = new Scanner(file);
				String[] tokens;
				if (file.length() > 0) {
					try {
						while (fileScanner.hasNextLine()) {
							tokens = fileScanner.nextLine().split("/");
							department.getHashTable().insert(
									new Student(tokens[0].trim(), Integer.parseInt(tokens[1].trim()), Double.parseDouble(tokens[2].trim()), tokens[3].trim()));
						}
					} catch (Exception e) {
						error.setContentText("Error! File Empty");
						error.show(); 
					} 
				}
				fileScanner.close();
			} catch (FileNotFoundException e) {
				error.setContentText("Warning: student data file is empty");
				error.show();
			}
		}
		return false;
	}

}
