package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimiiOikein() {
        assertEquals(varasto.toString(), "saldo = 0.0, vielä tilaa 10.0");
    }
    
    @Test
    public void negatiivinenTilavuusMuutetaanNollaksi() {
        varasto = new Varasto(-1);
        assertTrue(varasto.getTilavuus() == 0.0);
    }
    
    @Test
    public void eiVoiLisataNegatiivistaMaaraa() {
        double ensinMaara = varasto.getSaldo();
        varasto.lisaaVarastoon(-10);
        assertTrue(ensinMaara == varasto.getSaldo());
    }
    
    @Test
    public void lisatessaEiYlitetaTilavuutta() {
        varasto.lisaaVarastoon(varasto.getTilavuus() + 10);
        assertTrue(varasto.getSaldo() == varasto.getTilavuus());
    }
    
    @Test
    public void konstruktoriKahdellaMuuttujallaToimii() {
        varasto = new Varasto(20, 10);
        assertTrue(varasto.getSaldo() == 10 && varasto.getTilavuus() == 20);
    }
    
    @Test
    public void konstruktoriKahdellaMuuttujallaAsettaaNegatiivisenTilavuudenNollaksi() {
        varasto = new Varasto(-1, 10);
        assertTrue(varasto.getTilavuus() == 0);
    }
    
    @Test
    public void konstruktoriKahdellaMuuttujallaAsettaaNegatiivisenSaldonNollaksi() {
        varasto = new Varasto(10, -1);
        assertTrue(varasto.getSaldo() == 0);
    }
    
    @Test
    public void konstuktoriKahdellaMuuttujallaAsettaaSaldoksiKorkeintaanTilavuuden() {
        varasto = new Varasto(10, 20);
        assertTrue(varasto.getSaldo() == 10);
    }
    
    @Test
    public void varastostaEiVoiOttaaNegatiivistaMaaraa() {
        assertTrue(varasto.otaVarastosta(-10) == 0);
    }
    
    @Test
    public void varastostaVoidaanOttaaEnintaanSaldo() {
        varasto.lisaaVarastoon(10);
        assertTrue(9 == varasto.otaVarastosta(100));
    }

}