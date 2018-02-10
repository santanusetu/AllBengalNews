package com.santanu.chak.allbengalnews;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.thefinestartist.finestwebview.FinestWebView;

import java.util.ArrayList;
import java.util.List;

public class AllBengalNewsMainActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<Item>();
    CustomGridViewAdapter customGridAdapter;
    List<NewsPaperDetails> newsPaperDetailsList = new ArrayList<>();
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_all_bengal_news_main);

        try {
            addNewsKolkataData();

            //admob ads
            mAdView = (AdView) findViewById(R.id.adView);

            AdRequest adRequest = new AdRequest.Builder().build();
            /*AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    // Check the LogCat to get your test device ID
                    .addTestDevice("526F0D88D6693E7CA2D31B5F87C1AAF0")
                    .build();*/

            mAdView.loadAd(adRequest);

            //set grid view item
            for (int i = 0; i < newsPaperDetailsList.size(); i++) {
                gridArray.add(new Item((BitmapFactory.decodeResource(this.getResources(), newsPaperDetailsList.get(i).getNewsPaperImage())), newsPaperDetailsList.get(i).getNewspaperName()));
            }

            gridView = (GridView) findViewById(R.id.gvGridView1);
            customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, gridArray);
            gridView.setAdapter(customGridAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    //Toast.makeText(AllBengalNewsMainActivity.this,"----> "+ position + "  #Selected",Toast.LENGTH_SHORT).show();

                    openWebsite(newsPaperDetailsList.get(position).getNewspaperURL(), newsPaperDetailsList.get(position).getNewspaperName());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void openWebsite(String url, String newspaperName) {

        new FinestWebView.Builder(AllBengalNewsMainActivity.this)
                .titleDefault("News Kolkata - " + newspaperName)
                .titleColor(ContextCompat.getColor(this, R.color.colorAccent))
                .updateTitleFromHtml(false)
                .showIconBack(true)
                .showIconForward(true)
                .showUrl(false)
                .showMenuFind(true)
                .showMenuShareVia(true)
                .showMenuCopyLink(true)
                .showMenuOpenWith(true)
                .backPressToClose(true)

                .webViewSupportZoom(true)
                .webViewMediaPlaybackRequiresUserGesture(true)
                .webViewBuiltInZoomControls(true)
                .webViewAllowFileAccess(true)
                .webViewAllowContentAccess(true)
                .webViewLoadWithOverviewMode(true)
                .webViewSaveFormData(true)
                .webViewUseWideViewPort(true)
                .webViewSupportMultipleWindows(true)
                .webViewLoadsImagesAutomatically(true)
                .webViewJavaScriptEnabled(true)
                .webViewAllowUniversalAccessFromFileURLs(true)
                .webViewAppCacheEnabled(true)
                .webViewDomStorageEnabled(true)
                .webViewJavaScriptCanOpenWindowsAutomatically(true)
                .webViewNeedInitialFocus(true)
                .webViewOffscreenPreRaster(true)

                .setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit, R.anim.activity_close_enter, R.anim.activity_close_exit)
                .show(url);

        //  Uri uri = Uri.parse(url);
        //  Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //  startActivity(intent);
    }


    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }


    private void addNewsKolkataData() {
        newsPaperDetailsList.add(new NewsPaperDetails("Anandabazar Patrika", R.drawable.anandabazar_logo, "http://anandabazar.com/"));
        newsPaperDetailsList.add(new NewsPaperDetails("Bartaman", R.drawable.bartaman_logo, "http://www.bartamanpatrika.com/"));
        newsPaperDetailsList.add(new NewsPaperDetails("Ei Samay", R.drawable.eisamay_logo, "https://eisamay.indiatimes.com/"));
        newsPaperDetailsList.add(new NewsPaperDetails("Sanbad Pratidin", R.drawable.pratidin_logo, "http://www.sangbadpratidin.in/"));

        newsPaperDetailsList.add(new NewsPaperDetails("The StatesMan", R.drawable.statesman_logo, "https://www.thestatesman.com/"));
        newsPaperDetailsList.add(new NewsPaperDetails("The Telegraph", R.drawable.telegraph_logo, "http://www.telegraphindia.com/"));
        newsPaperDetailsList.add(new NewsPaperDetails("The Hindu", R.drawable.hindu_logo, "http://www.thehindu.com/"));
        newsPaperDetailsList.add(new NewsPaperDetails("Times of India", R.drawable.times_of_india_logo, "https://timesofindia.indiatimes.com/"));

        newsPaperDetailsList.add(new NewsPaperDetails("24 Ghanta", R.drawable.twentyfour_ghanta_logo, "http://zeenews.india.com/bengali"));
        newsPaperDetailsList.add(new NewsPaperDetails("Ebela", R.drawable.ebela_logo, "https://ebela.in"));
        newsPaperDetailsList.add(new NewsPaperDetails("ABP Ananda", R.drawable.abp_ananda_logo, "http://abpananda.abplive.in/"));
        newsPaperDetailsList.add(new NewsPaperDetails("Ganashakti", R.drawable.ganashakti_logo, "http://ganashakti.com/bengali/"));

        newsPaperDetailsList.add(new NewsPaperDetails("Jago Bangla", R.drawable.jago_bangla_logo, "http://aitcofficial.org/jago-bangla/"));
        newsPaperDetailsList.add(new NewsPaperDetails("Suprovat", R.drawable.suprovat_logo, "http://www.suprovat.com/"));
        newsPaperDetailsList.add(new NewsPaperDetails("UttarBanga Sambad", R.drawable.uttarbanga_sambad_logo, "http://uttarbangasambad.com"));
        newsPaperDetailsList.add(new NewsPaperDetails("Hindustan Times", R.drawable.hindustan_times_logo, "https://www.hindustantimes.com/"));
    }

}
