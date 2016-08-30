package com.zedx.firerocket.ui.wardrobe.presenters;

import com.zedx.firerocket.data.local.ClothesRepo;
import com.zedx.firerocket.ui.wardrobe.views.interactors.WardrobeViewListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by ajmac1005 on 29/08/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class WardrobePresenterImplTest {
    @Mock
    private WardrobeViewListener viewListener;

    @Mock
    private ClothesRepo clothesRepo;

    private WardrobePresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new WardrobePresenterImpl(viewListener);



    }



    @Test
    public void saveClothes() throws Exception {
        presenter.saveClothes("test",0);
        verify(viewListener).onSaveClothesSuccessful(0,"test");

    }

    @Test
    public void findAllPants() throws Exception {

    }

    @Test
    public void findAllShirts() throws Exception {

    }

    @Test
    public void saveFav() throws Exception {

    }

    @Test
    public void doShufflin() throws Exception {

    }

}