package ostanina.kk.unit_testing_2.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ostanina.kk.unit_testing_2.models.Note;

public class UtilTest {
        public static final String TIMESTAMP_1 = "05-2019";
        public static final Note TEST_NOTE_1 = new Note("Take out the trash", "It's garbage day tomorrow.", TIMESTAMP_1);

        public static final String TIMESTAMP_2 = "06-2019";
        public static final Note TEST_NOTE_2 = new Note("Anniversary gift", "Buy an anniversary gift.", TIMESTAMP_2);

        public static final List<Note> TEST_NOTES_LIST = Collections.unmodifiableList(
                new ArrayList<Note>(){{
                    add(new Note(1, "Take out the trash", "It's garbage day tomorrow.", TIMESTAMP_1));
                    add(new Note(2, "Anniversary gift", "Buy an anniversary gift.", TIMESTAMP_2));
                }}
        );
}
