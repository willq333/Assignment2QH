package au.edu.utas.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Open the database, so that we can read and write
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();
        Property property1 = new Property();
        property1.setAddress("742 Evergreen Terrace");
        property1.setBedrooms(4);
        property1.setPrice(250000);

        Property property2 = new Property();
        property2.setAddress("4352 Wisteria Lane");
        property2.setBedrooms(5);
        property2.setPrice(500000);

        PropertyTable.insert(db, property1);
        PropertyTable.insert(db, property2);
        final ArrayList<Property> properties = PropertyTable.selectAll(db);
        for (Property p: properties) {
            Log.d("Prop", p.getPropertyID() + ":" + p.getAddress());
        }
        final PropertyAdapter propertyListAdapter = new PropertyAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, properties);


        //List parts!
        ListView myList = findViewById(R.id.myList);
        myList.setAdapter(propertyListAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}
