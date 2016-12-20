/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.confluent.ksql.parser.tree;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.MoreObjects.toStringHelper;

public class Table
    extends QueryBody {

  public final boolean isSTDOut;
  Map<String, Expression> properties;
  private final QualifiedName name;

  public Table(QualifiedName name) {
    this(Optional.empty(), name, false);
  }

  public Table(QualifiedName name, boolean isSTDOut) {
    this(Optional.empty(), name, isSTDOut);
  }

  public Table(NodeLocation location, QualifiedName name) {
    this(Optional.of(location), name, false);
  }

  public Table(NodeLocation location, QualifiedName name, boolean isSTDOut) {
    this(Optional.of(location), name, isSTDOut);
  }

  private Table(Optional<NodeLocation> location, QualifiedName name, boolean isSTDOut) {
    super(location);
    this.name = name;
    this.isSTDOut = isSTDOut;
  }

  public QualifiedName getName() {
    return name;
  }

  public boolean isSTDOut() {
    return isSTDOut;
  }

  public Map<String, Expression> getProperties() {
    return properties;
  }

  public void setProperties(
      Map<String, Expression> properties) {
    this.properties = properties;
  }

  @Override
  public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
    return visitor.visitTable(this, context);
  }

  @Override
  public String toString() {
    return name.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Table table = (Table) o;
    return Objects.equals(name, table.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}
