package view;

import DB.DBRecord;
import DB.DataBaseHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by minchul on 2017-06-12.
 */
public class RankingView extends BaseView {

    private final List<DBRecord> data;
    private JPanel panel1;
    private JTable table1;
    private JButton button1;

    public RankingView(Object param) {
        super(param);

        DataBaseHelper dbHelper = DataBaseHelper.getInstance();
        data = dbHelper.readRecord();
        final DefaultTableModel model = (DefaultTableModel) table1.getModel();
        for(DBRecord record : data){
            final String[] row = new String[5];
            row[0] = String.valueOf(data.indexOf(record));
            row[1] = record.userName;
            row[2] = String.valueOf(record.score);
            row[3] = String.valueOf(record.semester);
            row[4] = record.charName;

            model.addRow(row);
        }

        model.fireTableDataChanged();

    }

    @Override
    public JPanel getContentPanel() {
        return panel1;
    }

    private void createUIComponents() {
        String[] columnNames = {"랭킹",
                "사용자 이름",
                "학점",
                "이수 학기",
                "캐릭터"};

        panel1 = new JPanel();
        table1 = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setColumnIdentifiers(columnNames);

        button1 = new JButton();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startView(MainView.class);
            }
        });
    }

    @Override
    public void onViewChanged() {

    }
}
