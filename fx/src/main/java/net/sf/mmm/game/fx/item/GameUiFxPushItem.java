/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.fx.item;

import net.sf.mmm.game.engine.item.GamePushItem;
import net.sf.mmm.game.fx.GameUiFxGame;

/**
 * JavaFx view for a {@link GamePushItem}.
 */
public class GameUiFxPushItem extends GameUiFxItem {

  private final GamePushItem item;

  /**
   * The constructor.
   *
   * @param item the {@link GamePushItem}.
   * @param fxGame the {@link #getFxParent() parent} {@link GameUiFxGame game}.
   */
  public GameUiFxPushItem(GamePushItem item, GameUiFxGame fxGame) {
    super(item, fxGame);
    this.item = item;
  }

  @Override
  public GamePushItem getGameAsset() {

    return this.item;
  }

}