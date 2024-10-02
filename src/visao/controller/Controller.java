package visao.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * <p>Classe de Controle <b>Controller</b>.</p>
 * <p>Classe responsavel por ser ouvinte de Eventos das <b>Views</b> do Sistema.</p>
 * <p><b>Implementa</b> a Interface java.awt.event.ActionListener e KeyListener.</p>
 * @author Leandro
 * @since  05/10/2015
 * @see    java.awt.event.ActionListener
 * @see    java.awt.event.KeyListener
 */
public abstract class Controller implements ActionListener, KeyListener {
    protected static final int ENTER = KeyEvent.VK_ENTER;
    protected static final int ESC   = KeyEvent.VK_ESCAPE;
    protected static final int F1    = KeyEvent.VK_F1;
    protected static final int F2    = KeyEvent.VK_F2;
    protected static final int F3    = KeyEvent.VK_F3;
    protected static final int F4    = KeyEvent.VK_F4;
    protected static final int F5    = KeyEvent.VK_F5;
    protected static final int F6    = KeyEvent.VK_F6;
    protected static final int F7    = KeyEvent.VK_F7;
    protected static final int F8    = KeyEvent.VK_F8;
    protected static final int F9    = KeyEvent.VK_F9;
    protected static final int F10   = KeyEvent.VK_F10;
    protected static final int F11   = KeyEvent.VK_F11;
    protected static final int F12   = KeyEvent.VK_F12;

    /**
     * <b>Metodo abstrato</b> para interpretar acoes.
     * @param event Evento de Acao.
     */
    @Override
    public abstract void actionPerformed(ActionEvent event);
    
    /**
     * <b>Metodo abstrato</b> para interpretar acoes da Tecla.
     * @param event Evento do Teclado.
     */
    @Override
    public abstract void keyPressed(KeyEvent event);

    @Override
    public void keyTyped(KeyEvent event) {}

    @Override
    public void keyReleased(KeyEvent event) {}
}