package windowGUI.component.workDB.restApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import java.util.ArrayList;

public interface RestApiForKeyWordsTable {
/*
* <Получение>
* запросы с помощью которых, можно получить данные из БД
* */
    @GET("admin/ui/getAllKeywords")
    Call<ArrayList<PojoKeyWords>> getListAllKeyWords();
/*
* </Получение>
* */

/*
* <Отправка>
* запросы с помощью которых, можно отправить данные в БД
* */
    @POST("admin/ui/addKeyword")
    Call<ResponseBody> addKeyWord(@Query("name") String keyWordName ,
                                  @Query("personId") int personID);// добавляет ключевое слово по ID личности в БД

    @POST("admin/ui/delKeyword")
    Call<ResponseBody> delKeyWord(@Query("id") int keyWordID);// удаляет ключевое слово по ID из БД

    @POST("admin/ui/modifyKeyword")
    Call<ResponseBody> modifyKeyWord(@Query("id") int keyWordID,
                                     @Query("name") String keyWordName ,
                                     @Query("personId") int personID);// редактирует имя ключевого слова и ID личности по ID ключевого слова(сам ID редоктировать нельзя)
/*
* </Отправка>
* */
}
