package windowGUI;

import java.awt.*;
import com.toedter.calendar.JDateChooser;

public class MyCalendar extends JDateChooser{

        public MyCalendar() {
            setBounds(20, 20, 200, 20);
            setDateFormatString("yyyy-MM-dd");

            EventQueue.invokeLater(() ->{
                try {
                    setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

}
