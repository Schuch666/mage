/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.cards.m;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.TopLibraryCardTypeCondition;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.effects.common.AddManaOfAnyColorEffect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.effects.common.continuous.GainAbilitySourceEffect;
import mage.abilities.effects.common.continuous.PlayWithTheTopCardRevealedEffect;
import mage.abilities.mana.SimpleManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Duration;
import mage.constants.Zone;

/**
 *
 * @author jeffwadsworth
 */
public class MulDayaChannelers extends CardImpl {

    private static final String rule1 = "As long as the top card of your library is a creature card, {this} gets +3/+3";

    public MulDayaChannelers(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{G}{G}");
        this.subtype.add(SubType.ELF);
        this.subtype.add(SubType.DRUID);
        this.subtype.add(SubType.SHAMAN);

        this.color.setGreen(true);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Play with the top card of your library revealed.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new PlayWithTheTopCardRevealedEffect()));

        // As long as the top card of your library is a creature card, Mul Daya Channelers gets +3/+3.
        ConditionalContinuousEffect effect = new ConditionalContinuousEffect(new BoostSourceEffect(3, 3, Duration.WhileOnBattlefield), new TopLibraryCardTypeCondition(CardType.CREATURE), rule1);
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, effect));

        // As long as the top card of your library is a land card, Mul Daya Channelers has "T: Add two mana of any one color."
        SimpleManaAbility manaAbility = new SimpleManaAbility(Zone.BATTLEFIELD, new AddManaOfAnyColorEffect(2), new TapSourceCost());
        effect = new ConditionalContinuousEffect(new GainAbilitySourceEffect(manaAbility, Duration.WhileOnBattlefield),
                new TopLibraryCardTypeCondition(CardType.LAND),
                "As long as the top card of your library is a land card, Mul Daya Channelers has \"{T}: Add two mana of any one color.\"");
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, effect));

    }

    public MulDayaChannelers(final MulDayaChannelers card) {
        super(card);
    }

    @Override
    public MulDayaChannelers copy() {
        return new MulDayaChannelers(this);
    }
}
