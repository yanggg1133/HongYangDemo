package com.hongyangdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hongyangdemo.domain.User;
import com.hongyangdemo.orm.ArticleDao;
import com.hongyangdemo.orm.DatabaseHelper;
import com.hongyangdemo.orm.UserDao;
import com.hongyangdemo.orm.domain.Article;
import com.hongyangdemo.utils.MLog;

import java.sql.SQLException;
import java.util.List;

/**
 *  Android ORMLite 框架的入门用法
 */
public class OrmLiteDbActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orm_lite_db);
    }

    public void addUser(View view) {
        User u1 = new User("zhy", "2B青年");
        DatabaseHelper helper = DatabaseHelper.getHelper(getApplicationContext());
        try {
            helper.getUserDao().create(u1);
            u1 = new User("zhy2", "2B青年");
            helper.getUserDao().create(u1);
            u1 = new User("zhy3", "2B青年");
            helper.getUserDao().create(u1);
            u1 = new User("zhy4", "2B青年");
            helper.getUserDao().create(u1);
            u1 = new User("zhy5", "2B青年");
            helper.getUserDao().create(u1);
            u1 = new User("zhy6", "2B青年");
            helper.getUserDao().create(u1);
            testList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteUser(View view) {
        DatabaseHelper helper = DatabaseHelper.getHelper(getApplicationContext());
        try {
            helper.getUserDao().deleteById(2);
            testList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(View view) {
        DatabaseHelper helper = DatabaseHelper.getHelper(getApplicationContext());
        try {
            User u1 = new User("zhy-android", "2B青年");
            u1.setId(3);
            helper.getUserDao().update(u1);
            testList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void testList() {
        DatabaseHelper helper = DatabaseHelper.getHelper(getApplicationContext());
        try {
            User u1 = new User("zhy-android", "2B青年");
            u1.setId(2);
            List<User> users = helper.getUserDao().queryForAll();
            Log.e("OrmLiteDbActivity", users.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addArticle(View view){
        com.hongyangdemo.orm.domain.User u = new com.hongyangdemo.orm.domain.User();
        u.setName("张鸿洋");
        new UserDao(getApplicationContext()).add(u);

        Article article = new Article();
        article.setTitle("ORMLite的使用");
        article.setUser(u);
        new ArticleDao(getApplicationContext()).add(article);

        Article article2 = new Article();
        article2.setTitle("ORMLite的高级使用");
        article2.setUser(u);
        new ArticleDao(getApplicationContext()).add(article2);
    }

    public void getArticleById(View view){
        Article article = new ArticleDao(getApplicationContext()).get(1);
        MLog.e(article.getUser() + " , " + article.getTitle());
    }

    public void getArticleWithUser(View view){
        Article article = new ArticleDao(getApplicationContext()).getArticleWithUser(1);
        MLog.e(article.getUser() + " , " + article.getTitle());
    }

    public void listArticlesByUserId(View view){
        List<Article> articles = new ArticleDao(getApplicationContext()).listByUserId(1);
        MLog.e(articles.toString());
    }

}
