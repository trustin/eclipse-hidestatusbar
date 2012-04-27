package net.gleamynode.eclipse.hidestatusbar;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class HideStatusBarActivator extends AbstractUIPlugin implements IStartup {

    @Override
    public void earlyStartup() {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
                HideStatusBarActivator.hideStatusBar();
            }
        });
    }

    private static void hideStatusBar() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        if (shell.isDisposed()) {
            return;
        }

        for (Control c: shell.getChildren()) {
            Class<?> cType = c.getClass();
            if (cType == Canvas.class || cType == Composite.class) {
                continue;
            }

            if (c.isDisposed()) {
                continue;
            }

            c.setVisible(false);
        }

        shell.layout();
    }
}
