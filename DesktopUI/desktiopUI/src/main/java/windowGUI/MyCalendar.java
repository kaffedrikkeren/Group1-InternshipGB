package windowGUI;

import java.awt.EventQueue;
import com.toedter.calendar.JDateChooser;

public class MyCalendar extends JDateChooser{

        public MyCalendar() {
            setBounds(20, 20, 200, 20);
            setDateFormatString("yyyy-MM-dd- HH:mm:ss");

            EventQueue.invokeLater(() ->{
                try {
                    setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
}