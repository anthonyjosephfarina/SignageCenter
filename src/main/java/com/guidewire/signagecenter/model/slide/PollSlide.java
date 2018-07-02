package com.guidewire.signagecenter.model.slide;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = SlideType.Values.POLL)
public class PollSlide extends AbstractSlide {


}
