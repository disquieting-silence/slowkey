package dsq.slowkey.vista.portrait;

import android.content.Context;
import dsq.slowkey.data.Option;
import dsq.slowkey.desk.KeyData;

import java.util.List;

public interface ColemakSections {
    List<List<Option<KeyData>>> generate(Context context, char[] topRow, int left, int right, char[] bottomRow);
}
