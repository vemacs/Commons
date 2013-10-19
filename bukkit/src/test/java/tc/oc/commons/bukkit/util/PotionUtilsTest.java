package tc.oc.commons.bukkit.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.*;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Tests for {@link PotionUtils}. */
@RunWith(JUnit4.class)
public class PotionUtilsTest {
    private Random random;

    @Before
    public void setUp() {
        this.random = new Random();
    }

    @Test
    public void simplePotionClassificationTest() {
        for (PotionEffectType effect : PotionUtils.potionEffectTypeImplications.keySet()) {
            int randomDuration = (this.random.nextInt(119) + 1) * 20;
            int randomLevel = this.random.nextInt(8) - 4;
            assertEquals("Mapped implication did not match score-determined implication (Level: " + randomLevel + ", Duration: " + randomDuration + ")", randomLevel != 0 ? (randomLevel > 0 ? PotionUtils.potionEffectTypeImplications : PotionUtils.inversePotionEffectTypeImplications).get(effect) : PotionUtils.PotionClassification.UNKNOWN, PotionUtils.PotionClassification.getClassificationForScore(PotionUtils.getScore(new PotionEffect(effect, randomDuration, randomLevel))));
        }
    }

    @Test
    public void riftCaseTest() {
        PotionCase baron = new PotionCase(
                new PotionEffect(PotionEffectType.FAST_DIGGING, 3600, 3),
                new PotionEffect(PotionEffectType.REGENERATION, 3600, 2),
                new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 3600, 1),
                new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 1),
                new PotionEffect(PotionEffectType.SPEED, 3600, 1),
                new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 1)
        );
        assertFalse("Rift Baron potion was not classified as <BENEFICIAL>", baron.isHarmful());
    }

    @After
    public void tearDown() {
        this.random = null;
    }

    @SuppressWarnings("unused")
    private class PotionCase {
        private final Set<PotionEffect> effects;

        protected PotionCase(PotionEffect... effects) {
            this.effects = new HashSet<>();

            Collections.addAll(this.effects, effects);
        }

        protected PotionCase(Collection<PotionEffect> effects) {
            this.effects = new HashSet<>();
            this.effects.addAll(effects);
        }

        protected Set<PotionEffect> getEffects() {
            return this.effects;
        }

        protected PotionUtils.PotionClassification classify() {
            return PotionUtils.getClassificationForEffects(this.effects);
        }

        protected boolean isHarmful() {
            return PotionUtils.isHarmful(this.effects);
        }
    }
}
