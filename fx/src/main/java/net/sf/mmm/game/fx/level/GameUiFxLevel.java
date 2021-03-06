/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.game.fx.level;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import net.sf.mmm.game.engine.Game;
import net.sf.mmm.game.engine.border.GameBorder;
import net.sf.mmm.game.engine.border.GameEdgeType;
import net.sf.mmm.game.engine.direction.GameDirection;
import net.sf.mmm.game.engine.field.GameField;
import net.sf.mmm.game.engine.level.GameLevel;
import net.sf.mmm.game.fx.GameUiFxGame;
import net.sf.mmm.game.fx.GameUiFxObject;
import net.sf.mmm.game.fx.border.GameUiFxBorder;
import net.sf.mmm.game.fx.field.GameUiFxField;

/**
 * JavaFx view for a {@link GameLevel}.
 */
public class GameUiFxLevel extends GridPane implements GameUiFxObject {

  private final GameUiFxGame game;

  private final GameLevel level;

  private boolean initialized;

  /**
   * The constructor.
   *
   * @param level the {@link GameLevel}.
   * @param game the {@link GameUiFxGame} owning this level.
   */
  public GameUiFxLevel(GameLevel level, GameUiFxGame game) {
    super();
    this.level = level;
    this.game = game;
    getStyleClass().add("level");
  }

  /**
   * Internal method to initialize this level.
   */
  public void initialize() {

    if (this.initialized) {
      return;
    }
    this.initialized = true;
    Game playGame = this.level.getGame();
    GameField startField = this.level.getStartField();
    GameDirection xDir = playGame.getDirectionX();
    GameDirection yDir = playGame.getDirectionY();
    GameDirection xDirInverse = xDir.getInverse();
    GameDirection yDirInverse = yDir.getInverse();
    GameField xStartField = startField;
    boolean showBorder = playGame.isShowBorder();
    int y = 0;
    while (xStartField != null) {
      GameField field = xStartField;
      int x = 0;
      if (showBorder) {
        if (y == 0) {
          addEdge(x, y);
          y++;
        }
        addBorder(field.getBorder(xDirInverse), x, y);
        addEdge(x, y + 1);
        x++;
      }
      while (field != null) {
        addField(field, x, y);
        if (showBorder) {
          if (y == 1) {
            addBorder(field.getBorder(yDirInverse), x, y - 1);
          }
          addBorder(field.getBorder(yDir), x, y + 1);
        }
        x++;
        GameBorder border = field.getBorder(xDir);
        if (showBorder) {
          if (y == 1) {
            addEdge(x, y - 1);
          }
          addBorder(border, x, y);
          addEdge(x, y + 1);
          x++;
        }
        field = border.getField(xDir);
      }
      GameBorder border = xStartField.getBorder(yDir);
      xStartField = border.getField(yDir);
      y++;
      if (showBorder) {
        y++;
      }
    }
  }

  @Override
  public GameUiFxGame getFxGame() {

    return this.game;
  }

  @Override
  public GameUiFxGame getFxParent() {

    return this.game;
  }

  private void addEdge(int x, int y) {

    Image image = this.game.getFxDataCache().getImage(GameEdgeType.DEFAULT);
    ImageView imageView = new ImageView(image);
    add(imageView, x, y);
  }

  private void addField(GameField field, int x, int y) {

    GameUiFxField fxField = this.game.getFxField(field);
    add(fxField, x, y);
  }

  private void addBorder(GameBorder border, int x, int y) {

    GameUiFxBorder fxBorder = this.game.getFxBorder(border);
    add(fxBorder, x, y);
  }

  /**
   * @return the {@link GameLevel}.
   */
  public GameLevel getPlayLevel() {

    return this.level;
  }

}
