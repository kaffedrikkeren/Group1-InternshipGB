package windowGUI.component.workDB.tables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import windowGUI.component.workDB.restApi.PojoPersons;
import windowGUI.component.workDB.restApi.RestApiForPersonTable;
/*
 * Класс-таблица, отвечающий за получение(отправку) данных из таблицы Person, в REST-сервер
 * */
public class PersonsTable extends ConnectServer {
    private static final RestApiForPersonTable REST_API_FOR_PERSON_TABLE = getRetrofit().create(RestApiForPersonTable.class);
    private static final ArrayList<Integer> LIST_ID = new ArrayList<>();
    private static final ArrayList<String> LIST_NAME = new ArrayList<>();
    private static final ArrayList<Boolean> LIST_ACTIVE = new ArrayList<>();
    private static final LinkedHashMap<Integer,String> LIST_ID_AND_NAME = new LinkedHashMap<>();
    private static final LinkedHashMap<String,Boolean> LIST_NAME_AND_ACTIVE = new LinkedHashMap<>();

    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */

    /*
     * метод, заполняющий списки данными из БД
     * */
    public static void infoAllPersons(){
        LIST_ID.clear();
        LIST_NAME.clear();
        LIST_ACTIVE.clear();
        try {
            Response<ArrayList<PojoPersons>> response = REST_API_FOR_PERSON_TABLE.getListAllPersons().execute();
            ArrayList<PojoPersons> list = response.body();
            for (int i = 0; i < list.size(); i++) {
                LIST_ID.add(list.get(i).getId());
                LIST_NAME.add(list.get(i).getName());
                LIST_ACTIVE.add(list.get(i).getActive());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * </Получение>
     * */

    /*
     * <Отправка>
     * запросы с помощью которых, можно отправить данные в БД
     * */

    /*
     * метод, добавляющий личность
     * */
    public static void addPerson(String personName, boolean personActive){
        try {
            Response<ResponseBody> response = REST_API_FOR_PERSON_TABLE.addPerson(personName, personActive).execute();
            System.out.println(response.raw());
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * метод, удаляющий личность
     * */
    public static void delPerson(int personID){
        try {
            Response<ResponseBody> response = REST_API_FOR_PERSON_TABLE.delPerson(personID).execute();
            System.out.println(response.raw());
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * метод, редактирующий личность
     * */
    public static void modifyPerson(int personID, String personName, boolean personActive){
        try {
            Response<ResponseBody> response = REST_API_FOR_PERSON_TABLE.modifyPerson(personID, personName, personActive).execute();
            System.out.println(response.raw());
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * </Отправка>
     * */

    /*
     * метод, возвращающий связанный спискок ID и имени личности
     * */
    public static LinkedHashMap<Integer, String> getListIDAndName() {
        for (int i = 0; i < getListID().size(); i++) {
            LIST_ID_AND_NAME.put(getListID().get(i),getListName().get(i));
        }
        return LIST_ID_AND_NAME;
    }

    /*
     * метод, возвращающий связанный спискок имени и фктивности личности
     * */
    public static LinkedHashMap<String, Boolean> getListNameAndActive() {
        for (int i = 0; i < getListID().size(); i++) {
            LIST_NAME_AND_ACTIVE.put(getListName().get(i),getListActive().get(i));
        }
        return LIST_NAME_AND_ACTIVE;
    }

    /*
     * <getters>
     * */
    public static ArrayList<Integer> getListID() {
        return LIST_ID;
    }
    public static ArrayList<String> getListName(){
        return LIST_NAME;
    }
    private static ArrayList<Boolean> getListActive(){
        return LIST_ACTIVE;
    }
    /*
     * </getters>
     * */
}
