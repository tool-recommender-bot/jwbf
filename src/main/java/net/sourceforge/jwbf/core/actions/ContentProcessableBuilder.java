package net.sourceforge.jwbf.core.actions;

import java.util.Deque;

import net.sourceforge.jwbf.core.actions.util.HttpAction;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Queues;

public class ContentProcessableBuilder {

  private ImmutableList<HttpAction> actions;
  private final HttpActionClient hac;

  public ContentProcessableBuilder(HttpActionClient hac) {
    this.hac = hac;
  }

  public <T> ResponseHandler<T> build() {
    Deque<HttpAction> actionQueue = Queues.newArrayDeque(actions);
    return new ResponseHandler<T>(actionQueue) {

      @Override
      public T get() {
        String performAction = hac.performAction(this);
        return (T) latestResponse;
      }
    };
  }

  public static ContentProcessableBuilder create(HttpActionClient hac) {
    return new ContentProcessableBuilder(hac);
  }

  public ContentProcessableBuilder withActions(HttpAction... actions) {
    this.actions = ImmutableList.copyOf(actions);
    return this;
  }

}
