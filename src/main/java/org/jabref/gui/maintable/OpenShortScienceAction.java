package org.jabref.gui.maintable;

import java.io.IOException;
import java.util.List;

import javafx.beans.binding.BooleanExpression;

import org.jabref.gui.DialogService;
import org.jabref.gui.StateManager;
import org.jabref.gui.actions.SimpleCommand;
import org.jabref.gui.desktop.JabRefDesktop;
import org.jabref.logic.l10n.Localization;
import org.jabref.logic.util.ExternalLinkCreator;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.StandardField;

import static org.jabref.gui.actions.ActionHelper.isFieldSetForSelectedEntry;
import static org.jabref.gui.actions.ActionHelper.needsEntriesSelected;

public class OpenShortScienceAction extends SimpleCommand {
    private final DialogService dialogService;
    private final StateManager stateManager;

    public OpenShortScienceAction(DialogService dialogService, StateManager stateManager) {
        this.dialogService = dialogService;
        this.stateManager = stateManager;

        BooleanExpression fieldIsSet = isFieldSetForSelectedEntry(StandardField.TITLE, stateManager);
        this.executable.bind(needsEntriesSelected(1, stateManager).and(fieldIsSet));
    }

    @Override
    public void execute() {
        stateManager.getActiveDatabase().ifPresent(databaseContext -> {
            final List<BibEntry> bibEntries = stateManager.getSelectedEntries();

            if (bibEntries.size() != 1) {
                dialogService.notify(Localization.lang("This operation requires exactly one item to be selected."));
                return;
            }
            ExternalLinkCreator.getShortScienceSearchURL(bibEntries.get(0)).ifPresent(url -> {
                try {
                    JabRefDesktop.openExternalViewer(databaseContext, url, StandardField.URL);
                } catch (IOException ex) {
                    dialogService.showErrorDialogAndWait(Localization.lang("Unable to open ShortScience."), ex);
                }
            });
        });
    }
}