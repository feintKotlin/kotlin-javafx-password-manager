package com.feint.fx

import com.feint.fx.data.PwdContract
import com.feint.fx.data.PwdDbHelper
import com.feint.fx.entity.Pwd
import com.jfoenix.controls.JFXTextField
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.control.cell.TextFieldTableCell

class MainController{
    @FXML
    private lateinit var errLabel: Label;

    @FXML
    private lateinit var pwdTable: TableView<Pwd>;

    @FXML
    private lateinit var pwdField: JFXTextField;

    @FXML
    private lateinit var topicField: JFXTextField;

    val pwdHelper: PwdDbHelper = PwdDbHelper();

    @FXML
    fun insert(action: ActionEvent){
        if(pwdField.text.trim()==""||topicField.text.trim()=="") {
            errLabel.text = "信息没有填写完全"
            return
        }
        val pwd= Pwd(0,pwdField.text,topicField.text);
        pwdHelper.insert(pwd);
        select(action);
    }

    @FXML
    fun select(action: ActionEvent){
        errLabel.text=""
        pwdTable.isEditable=true;
        pwdTable.columns.clear();
        pwdTable.items= FXCollections.observableArrayList(pwdHelper.select());
        val idCol= TableColumn<Pwd,Int>(PwdContract.COLUMN_ID);
        idCol.cellValueFactory= PropertyValueFactory(PwdContract.COLUMN_ID);
        pwdTable.columns.addAll(idCol,
                createTableColumn(PwdContract.PwdEntity.COLUMN_PWD,fun(pwd, value){pwd.pwd=value}),
                createTableColumn(PwdContract.PwdEntity.COLUMN_TOPIC,fun(pwd, value){pwd.topic=value}));
    }

    fun createTableColumn(colName:String,editor:(pwd: Pwd, String)->Unit): TableColumn<Pwd, String> {
        val idCol= TableColumn<Pwd,String>(colName);
        idCol.cellValueFactory= PropertyValueFactory<Pwd,String>(colName);

        idCol.cellFactory= TextFieldTableCell.forTableColumn();
        idCol.setOnEditCommit (fun (t: TableColumn.CellEditEvent<Pwd, String>){
            val pwd=t.tableView.items.get(t.tablePosition.row) as Pwd;
            editor(pwd,t.newValue);
        })
        return idCol;
    }
}