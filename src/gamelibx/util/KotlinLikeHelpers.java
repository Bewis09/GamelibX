package gamelibx.util;

import java.util.function.Consumer;

public class KotlinLikeHelpers {
    public static <T> T apply(T it, Consumer<T> block) {
        block.accept(it);
        return it;
    }
}
