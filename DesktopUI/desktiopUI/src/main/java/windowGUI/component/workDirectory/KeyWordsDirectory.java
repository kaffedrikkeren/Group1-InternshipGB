package windowGUI.component.workDirectory;

import windowGUI.component.workDB.processingData.ProcessingData;
import windowGUI.editingDirectoryWindow.add.AddKeyWordWindow;
import windowGUI.editingDirectoryWindow.delete.DelKeyWordWindow;
import windowGUI.editingDirectoryWindow.edit.EditKeyWordWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import static java.awt.GridBagConstraints.*;
/*
 * Класс-справочник, отвечающий за функциональную деятельность справочника KeyWords
 * */
public class KeyWordsDirectory extends Directory{
    private static final String NAME_TAB = "Ключевые слова";

    private static String namePerson;
    private static String nameKeyWord ;

    public KeyWordsDirectory() {
        setNameTab(NAME_TAB);

        fillOptionsPanel();

        addActionListenerForListPerson();
        getBtnConfirm().addActionListener(this::visibleDataTable);
    }

    @Override
    public void fillOptionsPanel() {
        getGBL().setConstraints(getHeadLinePerson(),getCGBL().configGBC(EAST,1,false));
        getOptionsPanel().add(getHeadLinePerson());
        getGBL().setConstraints(getListPersons(), getCGBL().configGBC(2,false));
        getOptionsPanel().add(getListPersons());

        getGBL().setConstraints(getBtnConfirm(), getCGBL().configGBC(REMAINDER,true));
        getOptionsPanel().add(getBtnConfirm());
    }

    @Override
    public void initNamePerson(ActionEvent actionEvent) {
        JComboBox box = (JComboBox)actionEvent.getSource();
        namePerson = (String)box.getSelectedItem();
    }

    @Override
    public void visibleDataTable(ActionEvent actionEvent){
        if(namePerson == null || namePerson.equals(ProcessingData.getNotChosen())){
            JOptionPane.showMessageDialog(null,
                    "Для просмотра ключевых слов необходимо выбрать \""  + getHeadLinePerson().getText() + "\" ",
                    "Не инициализированы поля",
                    JOptionPane.WARNING_MESSAGE);
        }
        for (int i = 0; i <  getPanelDirectory().getComponents().length; i++) {
            if( getPanelDirectory().getComponents()[i].equals(dataScrollPane)){
                getPanelDirectory().remove(dataScrollPane);
            }
        }

        dataTable = new JTable(getPKeyWordsT().getArrayFillTable(namePerson, getColumnNames().length), getColumnNames());
        dataScrollPane = new JScrollPane(dataTable);
        getPanelDirectory().add(dataScrollPane, BorderLayout.CENTER);
        dataTable.getSelectionModel().addListSelectionListener(this::initSelectedRow);
        dataScrollPane.setVisible(true);
        getPanelDirectory().updateUI();
    }

    @Override
    public void visibleWindowAdd(ActionEvent actionEvent){
        if(namePerson == null || namePerson.equals("Не выбранно")){
            JOptionPane.showMessageDialog(null,
                    "Для добавления ключевых слов необходимо выбрать \""  + getHeadLinePerson().getText() + "\" ",
                    "Не инициализированы поля",
                    JOptionPane.WARNING_MESSAGE);
        }else {
            new AddKeyWordWindow(getBtnAdd().getText() + " новое ключевое слово для личности: " + namePerson,
                    getPPersonT().getIDPersonByNamePerson(namePerson));
        }
    }

    @Override
    public void initSelectedRow(ListSelectionEvent selectionEvent){
        TableModel model = dataTable.getModel();
        Object value = model.getValueAt(dataTable.getSelectedRow(), 0);
        nameKeyWord = (String) value;
    }

    @Override
    public void visibleWindowDel(ActionEvent actionEvent) {
        if(nameKeyWord == null ){
            JOptionPane.showMessageDialog(null,
                    "Для удаления ключевого слова необходимо выбрать ключевое слово из списка",
                    "Не инициализированы поля",
                    JOptionPane.WARNING_MESSAGE);
        }else {
            new DelKeyWordWindow(getBtnDelete().getText() + " ключевое слово ", nameKeyWord, getPKeyWordsT().getIDKeyWordByNameKeyWord(nameKeyWord));
        }
    }

    @Override
    public void visibleWindowEdit(ActionEvent actionEvent) {
        if(nameKeyWord == null){
            JOptionPane.showMessageDialog(null,
                    "Для редактирования ключевого слова необходимо выбрать ключевое слово из списка",
                    "Не инициализированы поля",
                    JOptionPane.WARNING_MESSAGE);
        }else {
            new EditKeyWordWindow(getBtnEdit().getText() + " ключевое слово ",
                    nameKeyWord,
                    getPKeyWordsT().getIDKeyWordByNameKeyWord(nameKeyWord),
                    getPPersonT().getIDPersonByNamePerson(namePerson));

        }
    }
}
