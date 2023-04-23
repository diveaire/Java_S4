# main() class
MAIN = ScreenGUI.Windows

# Folders
SRCDIR = GraphEditor
GRAPHEDIR = $(SRCDIR)/Graphe
FORMEDIR = $(GRAPHEDIR)/Forme
SCREENGUI = $(SRCDIR)/ScreenGUI
CLASSDIR = class

# Compilation flags
JFLAGS = -sourcepath $(SRCDIR)

# Source files
FORMESFILES = $(wildcard $(FORMEDIR)/*.java)
SCREENFILES = $(wildcard $(SCREENGUI)/*.java)
MAINFILES = $(wildcard $(GRAPHEDIR)/*.java)
SRCFILES = \
        $(FORMESFILES) \
        $(SCREENFILES) \
        $(MAINFILES)

# Class files
CLASSFILES = $(patsubst $(SRCDIR)/%.java,$(CLASSDIR)/%.class,$(SRCFILES))

# Java compilation
$(CLASSDIR)/%.class: $(SRCDIR)/%.java
	javac $(JFLAGS) $< -d $(CLASSDIR)

# Start the project
start: $(CLASSFILES)
	java -classpath $(CLASSDIR) $(MAIN)

# Delete all compiled class
clean:
	rm -rf $(CLASSDIR)/*
